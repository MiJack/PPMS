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

import com.google.common.collect.Maps;
import com.mijack.ppms.api.RpcResult;
import com.mijack.ppms.enums.Enums;
import com.mijack.ppms.token.annotations.For;
import com.mijack.ppms.token.api.TokenServiceFacade;
import com.mijack.ppms.token.dto.GeneratedToken;
import com.mijack.ppms.token.dto.Token;
import com.mijack.ppms.token.dto.TokenGenerateDto;
import com.mijack.ppms.token.dto.TokenRpcCode;
import com.mijack.ppms.token.dto.TokenType;
import com.mijack.ppms.token.service.TokenService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Mi&Jack
 */
@Service
public class TokenServiceFacadeImpl implements TokenServiceFacade {
    private Map<TokenType, TokenService> tokenServiceMap = Maps.newHashMap();

    @Autowired
    public TokenServiceFacadeImpl(List<TokenService> tokenServices) {
        if (CollectionUtils.isEmpty(tokenServices)) {
            return;
        }
        for (TokenService tokenService : tokenServices) {
            Class<? extends TokenService> clazz = tokenService.getClass();
            For annotation = clazz.getAnnotation(For.class);
            if (annotation != null) {
                TokenType tokenType = annotation.tokenType();
                tokenServiceMap.put(tokenType, tokenService);
            }
        }
    }

    /**
     * 生成token
     *
     * @param tokenGenerateDto
     * @return 生成的邀请token
     */
    @Override
    public RpcResult<GeneratedToken> generateToken(TokenGenerateDto tokenGenerateDto) {
        TokenService tokenService = findTokenService(tokenGenerateDto);
        GeneratedToken generatedToken = tokenService.generateToken(tokenGenerateDto);
        if (generatedToken != null) {
            return TokenRpcCode.ResultOk.wrapResult(generatedToken);
        }
        return TokenRpcCode.TokenGenerationFailed.wrapResult(null);
    }

    private TokenService findTokenService(TokenGenerateDto tokenGenerateDto) {
        long tokenTypeCode = tokenGenerateDto != null ? tokenGenerateDto.getTokenType() : -1;
        TokenType tokenType = Enums.idOf(TokenType.class, tokenTypeCode);
        if (tokenType == null) {
            TokenRpcCode.TokenTypeCodeNotSupported.throwException(tokenTypeCode);
        }
        TokenService tokenService = tokenServiceMap.get(tokenType);
        if (tokenService == null) {
            TokenRpcCode.TokenTypeNotSupported.throwException(tokenType);
        }
        return tokenService;
    }

    /**
     * 检查token的有效性
     *
     * @param token
     * @return
     */
    @Override
    public RpcResult<Boolean> checkToken(Token token) {
        return null;
    }

    /**
     * 根据token还原生成token的源数据
     *
     * @param token
     * @return 生成Token对应的源数据
     */
    @Override
    public RpcResult<TokenGenerateDto> reverseToken(Token token) {
        return null;
    }

    /**
     * 使token失效
     *
     * @param token
     * @return
     */
    @Override
    public RpcResult<Boolean> invalidToken(Token token) {
        return null;
    }

    /**
     * 延长token的有效期
     * date
     *
     * @param token
     * @param expireDuration 使未失效的token的失效时间点向后延长的时间
     * @return 生成的权限
     */
    @Override
    public RpcResult<GeneratedToken> extendTokenExpireTime(Token token, long expireDuration) {
        return null;
    }
}
