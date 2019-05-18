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

package com.mijack.ppms.user.api;

import com.mijack.ppms.api.RpcResult;
import com.mijack.ppms.user.dto.Role;
import com.mijack.ppms.user.dto.RoleStatus;
import com.mijack.ppms.user.dto.UserRequirement;

import java.util.List;

/**
 * @author Mi&Jack
 */
public interface UserRoleService {
    /**
     * 列举用户的所有角色
     *
     * @param userId
     * @return
     */
    RpcResult<List<RoleStatus>> listUserRole(long userId);

    /**
     * 判断用户是否包含某些角色
     *
     * @param userId
     * @param roles
     * @return
     */
    RpcResult<Boolean> hasAnyRole(long userId, List<Role> roles);

    /**
     * 判断用户是否包含以下所有角色
     *
     * @param userId
     * @param roles
     * @return
     */
    RpcResult<Boolean> hasAllRole(long userId, List<Role> roles);

    /**
     * 为用户添加角色
     *
     * @param userId
     * @param operatorId
     * @param roles
     * @return
     */
    RpcResult<Boolean> addRoles(long userId, long operatorId, List<Role> roles);

    /**
     * 为用户停用角色
     *
     * @param userId
     * @param operatorId
     * @param roles
     * @return
     */
    RpcResult<Boolean> disableRoles(long userId, long operatorId, List<Role> roles);

    /**
     * 为用户移除角色
     *
     * @param userId
     * @param operatorId
     * @param roles
     * @return
     */
    RpcResult<Boolean> removeRoles(long userId, long operatorId, List<Role> roles);

}
