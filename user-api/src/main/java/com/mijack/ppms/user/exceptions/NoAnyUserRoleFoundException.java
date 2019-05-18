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

package com.mijack.ppms.user.exceptions;

import com.mijack.ppms.exceptions.RpcException;
import com.mijack.ppms.user.dto.Role;
import com.mijack.ppms.user.dto.UserRpcCode;
import com.sun.deploy.util.StringUtils;

import java.util.Set;

/**
 * @author Mi&Jack
 */
public class NoAnyUserRoleFoundException extends RpcException {
    private final Long userId;
    private final Set<Role> checkRoles;

    public NoAnyUserRoleFoundException(Long userId, Set<Role> checkRoles) {
        super(UserRpcCode.NoAnyUserRoleFound.toException(userId, StringUtils.join(checkRoles, ",")));
        this.userId = userId;
        this.checkRoles = checkRoles;
    }
}
