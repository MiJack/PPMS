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

import com.mijack.ppms.user.dto.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

/**
 * @author Mi&Jack
 */
public class UserRole implements GrantedAuthority {
    private Role role;
    public static final String PREFIX = "ROLE_";


    @Override
    public String getAuthority() {
        return PREFIX + role.name().toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return role == userRole.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
