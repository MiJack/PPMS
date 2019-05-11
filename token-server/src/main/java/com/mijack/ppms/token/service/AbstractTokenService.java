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

package com.mijack.ppms.token.service;

import com.mijack.ppms.token.dto.GeneratedToken;
import com.mijack.ppms.token.dto.Token;
import com.mijack.ppms.token.dto.TokenGenerateDto;
import com.mijack.ppms.token.dto.TokenRpcCode;

/**
 * @author Mi&Jack
 */
public abstract class AbstractTokenService<T> implements TokenService {

    /**
     * 生成token
     *
     * @param tokenGenerateDto
     * @return 生成的邀请token
     */
    public final GeneratedToken generateToken(TokenGenerateDto tokenGenerateDto) {
        T param = parseParams(tokenGenerateDto);
        return generateToken(tokenGenerateDto,param);
    }

    protected abstract T parseParams(TokenGenerateDto tokenGenerateDto);

    protected abstract GeneratedToken generateToken(TokenGenerateDto tokenGenerateDto,T param);


    /**
     * 使token失效
     *
     * @return
     */
    public void invalidToken(Token token) {
        throw TokenRpcCode.InvaildTokenNotSupported.toException(token.getTokenType());
    }

    /**
     * 延长token的有效期
     *
     * @param token
     * @param expireDuration 使未失效的token的失效时间点向后延长的时间
     * @return 生成的权限
     */
    public final GeneratedToken extendTokenExpireTime(Token token, long expireDuration) {
        if (!checkToken(token)) {
            throw TokenRpcCode.TokenInvaild.toException();
        }

        return extendTokenExpireTimeInner(token, expireDuration);
    }

    /**
     * 延长token的有效期
     *
     * @param token
     * @param expireDuration 使未失效的token的失效时间点向后延长的时间
     * @return 生成的权限
     */
    protected GeneratedToken extendTokenExpireTimeInner(Token token, long expireDuration) {
        throw TokenRpcCode.ExtendTokenExpireTimeNotSupported.toException(token.getTokenType());

    }

    /**
     * 根据token还原生成token的源数据
     *
     * @param token
     * @return 生成Token对应的源数据
     */
    @Override
    public TokenGenerateDto reverseToken(Token token) {
        throw TokenRpcCode.ReverseTokenNotSupported.toException(token.getTokenType());
    }

}
