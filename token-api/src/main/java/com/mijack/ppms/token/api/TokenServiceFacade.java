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

package com.mijack.ppms.token.api;

import com.mijack.ppms.api.RpcResult;
import com.mijack.ppms.token.dto.GeneratedToken;
import com.mijack.ppms.token.dto.Token;
import com.mijack.ppms.token.dto.TokenGenerateDto;


/**
 * @author Mi&Jack
 */
public interface TokenServiceFacade {
    /**
     * 生成token
     *
     * @param tokenGenerateDto
     * @return 生成的邀请token
     */
    RpcResult<GeneratedToken> generateToken(TokenGenerateDto tokenGenerateDto);

    /**
     * 检查token的有效性
     *
     * @param token
     * @return
     */
    RpcResult<Boolean> checkToken(Token token);

    /**
     * 根据token还原生成token的源数据
     *
     * @param token
     * @return 生成Token对应的源数据
     */
    RpcResult<TokenGenerateDto> reverseToken(Token token);

    /**
     * 使token失效
     *
     * @param token
     * @return
     */
    RpcResult<Boolean> invalidToken(Token token);

    /**
     * 延长token的有效期
     * date
     *
     * @param token
     * @param expireDuration 使未失效的token的失效时间点向后延长的时间
     * @return 生成的权限
     */
    RpcResult<GeneratedToken> extendTokenExpireTime(Token token, long expireDuration);
}
