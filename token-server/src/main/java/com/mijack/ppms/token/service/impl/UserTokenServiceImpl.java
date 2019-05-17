/*
 * Copyright 2019 Mi&Jack
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mijack.ppms.token.service.impl;

import com.mijack.ppms.common.CollectionHelper;
import com.mijack.ppms.token.data.UserTokenParam;
import com.mijack.ppms.token.dto.GeneratedToken;
import com.mijack.ppms.token.dto.Token;
import com.mijack.ppms.token.dto.TokenGenerateDto;
import com.mijack.ppms.token.dto.TokenRpcCode;
import com.mijack.ppms.token.dto.TokenType;
import com.mijack.ppms.token.service.AbstractTokenService;
import com.mijack.ppms.utils.Ip;
import com.mijack.ppms.utils.IpUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.hashids.Hashids;
import org.springframework.stereotype.Component;

/**
 * 生成加密的token用于rpc调用时对用户的密码进行加密，不支持逆向还原、失效、延长有效期等操作
 *
 * @author Mi&Jack
 */
@Component
public class UserTokenServiceImpl extends AbstractTokenService<UserTokenParam> {
    Hashids hashids = new Hashids("UserTokenService");

    @Override
    protected UserTokenParam parseParams(TokenGenerateDto tokenGenerateDto) {
        if (tokenGenerateDto.getTokenType() != TokenType.UserToken.id()) {
            throw new IllegalStateException("UserTokenService 不支持 TokenTypeCode = " + tokenGenerateDto.getTokenType());
        }
        Long requestTime = tokenGenerateDto.getParameter("requestTime", Long.class);
        String clientIp = tokenGenerateDto.getParameter("clientIp", "0.0.0.0");
        Ip ip = IpUtil.parseIp(clientIp);
        if (ip == null) {
            throw TokenRpcCode.ParameterError.toException("clientIp", clientIp);
        }
        return new UserTokenParam(requestTime, ip);
    }

    @Override
    protected GeneratedToken generateToken(TokenGenerateDto tokenGenerateDto, UserTokenParam param) {
        if (tokenGenerateDto.getTokenType() != TokenType.UserToken.id()) {
            throw new IllegalStateException("UserTokenService 不支持 TokenTypeCode = " + tokenGenerateDto.getTokenType());
        }
        Ip ip = param.getIp();
        long expireTimeMillis = param.getRequestTime() + DateUtils.MILLIS_PER_DAY;
        long ipValue = (((ip.getGroup0() * 256) + ip.getGroup1()) * 256 + ip.getGroup2()) * 256 + ip.getGroup3();
        String token = hashids.encode(expireTimeMillis, ipValue);
        GeneratedToken generatedToken = new GeneratedToken();
        generatedToken.setToken(token);
        generatedToken.setTokenType(tokenGenerateDto.getTokenType());
        generatedToken.setExpireTimeMillis(expireTimeMillis);
        return generatedToken;
    }

    /**
     * 检查token的有效性
     *
     * @param token
     * @return
     */
    @Override
    public Boolean checkToken(Token token) {
        if (token.getTokenType() != TokenType.UserToken.id()) {
            throw new IllegalStateException("UserTokenService 不支持 TokenTypeCode = " + token.getTokenType());
        }
        long currentTimeMillis = System.currentTimeMillis();
        long[] decode = hashids.decode(token.getToken());
        if (CollectionHelper.size(decode) != 2) {
            throw TokenRpcCode.TokenError.toException();
        }
        long expireTimeMillis = decode[0];
        return currentTimeMillis < expireTimeMillis;
    }
}
