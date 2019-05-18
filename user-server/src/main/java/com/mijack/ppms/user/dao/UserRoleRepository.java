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

package com.mijack.ppms.user.dao;

import com.mijack.ppms.user.dto.RoleStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mi&Jack
 */
@Repository
public interface UserRoleRepository {
    /**
     * 列举用户所有（未被禁用、被禁用）的角色
     *
     * @param userId
     * @return
     */
    List<RoleStatus> listAllUserRole(long userId);

    /**
     * 更新给定的角色状态
     *
     * @param roleStatuses
     */
    void updateUserRole(List<RoleStatus> roleStatuses);

    /**
     * 创建给定的角色状态
     *
     * @param roleStatuses
     */
    void createUserRoles(List<RoleStatus> roleStatuses);

    /**
     * 移除给定的角色状态
     *
     * @param existRoleStatuses
     */
    void removeUserStatus(List<RoleStatus> existRoleStatuses);
}
