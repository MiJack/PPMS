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

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * @author Mi&Jack
 */
public class PortalSecurityExpressionRoot extends WebSecurityExpressionRoot {
    private String httpRequest;
    public HttpServletRequest request;

    public PortalSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
        httpRequest = fi.getFullRequestUrl();
        request = fi.getHttpRequest();
    }

    public boolean isLocalhost() {
        return "localhost".equals(URI.create(httpRequest).getHost())
                || hasIpAddress("127.0.0.1") || hasIpAddress("192.168.1.0/24");
    }

}

