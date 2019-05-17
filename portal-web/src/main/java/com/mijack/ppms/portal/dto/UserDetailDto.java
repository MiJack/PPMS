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

package com.mijack.ppms.portal.dto;

import lombok.Data;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mi&Jack
 */
@Data
public class UserDetailDto implements UserDetails, CredentialsContainer {
    private final long userId;
    private String password;
    private final String username;
    private final Set<UserRole> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    @Override
    public void eraseCredentials() {
        password = "";
    }

    public List<String> getAuthorityCodes() {
        if (CollectionUtils.isEmpty(authorities)) {
            return Collections.EMPTY_LIST;
        }
        return authorities.stream().map(UserRole::getAuthority).collect(Collectors.toList());
    }
}
