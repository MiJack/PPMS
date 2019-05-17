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

package com.mijack.ppms.portal.security;

import com.mijack.ppms.api.RpcResult;
import com.mijack.ppms.portal.dto.UserDetailDto;
import com.mijack.ppms.token.api.TokenServiceFacade;
import com.mijack.ppms.token.dto.GeneratedToken;
import com.mijack.ppms.token.dto.TokenGenerateDto;
import com.mijack.ppms.token.dto.TokenType;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Mi&Jack
 */
@Component
public class LoginAuthenticationSuccessHandler extends
        AbstractAuthenticationTargetUrlRequestHandler implements
        AuthenticationSuccessHandler {
    public static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationSuccessHandler.class);
    @Reference
    TokenServiceFacade tokenServiceFacade;

    public LoginAuthenticationSuccessHandler() {
        setDefaultTargetUrl("/index.html");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(request, response);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to "
                    + targetUrl);
            return;
        }

        UserDetailDto userDetailDto = AuthenticationUtils.currentUser();
        TokenGenerateDto tokenGenerateDto = new TokenGenerateDto();
        tokenGenerateDto.setBizType(TokenType.UserAuthToken.name());
        tokenGenerateDto.setTokenType(TokenType.UserAuthToken.id());
        tokenGenerateDto.setParameter("userId", String.valueOf(userDetailDto.getUserId()));
        tokenGenerateDto.setParameter("authorities", StringUtils.join(userDetailDto.getAuthorityCodes(), ","));
        RpcResult<GeneratedToken> restfulToken = tokenServiceFacade.generateToken(tokenGenerateDto);
        if (restfulToken.isSuccuess() && restfulToken.getData() != null) {
            response.addCookie(new Cookie("restfulToken", restfulToken.getData().getToken()));
            response.addCookie(new Cookie("restfulTokenExpireTimeMillis", String.valueOf(restfulToken.getData().getExpireTimeMillis())));
        } else {
            logger.info("apply restful token failed:");
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

        clearAuthenticationAttributes(request);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}

