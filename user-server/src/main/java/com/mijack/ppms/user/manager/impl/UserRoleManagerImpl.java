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

package com.mijack.ppms.user.manager.impl;

import com.mijack.ppms.enums.Enums;
import com.mijack.ppms.user.dao.UserRoleRepository;
import com.mijack.ppms.user.dto.Role;
import com.mijack.ppms.user.dto.RoleStatus;
import com.mijack.ppms.user.dto.UserRequirement;
import com.mijack.ppms.user.manager.UserRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mi&Jack
 */
@Component
public class UserRoleManagerImpl implements UserRoleManager {
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 列举用户所有未被禁用的角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> listUserRole(long userId) {
        return listAllUserRole(userId).stream()
                .filter(roleStatus -> roleStatus.getStatus() == RoleStatus.STATUS_ENABLE)
                .map(roleStatus -> Enums.idOf(Role.class, roleStatus.getRoleId()))
                .collect(Collectors.toList());
    }

    /**
     * 列举用户所有（未被禁用和未被禁用）的角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<RoleStatus> listAllUserRole(long userId) {
        return userRoleRepository.listAllUserRole(userId);
    }

    /**
     * 更新UserRole状态
     *
     * @param roleStatuses
     */
    @Override
    public void updateUserRoles(List<RoleStatus> roleStatuses) {
        userRoleRepository.updateUserRole(roleStatuses);
    }

    /**
     * 创建UserRole状态
     *
     * @param roleStatuses
     */
    @Override
    public void createUserRoles(List<RoleStatus> roleStatuses) {
        userRoleRepository.createUserRoles(roleStatuses);
    }

    /**
     * 移除UserRole
     *
     * @param existRoleStatuses
     */
    @Override
    public void removeUserStatus(List<RoleStatus> existRoleStatuses) {
        userRoleRepository.removeUserStatus(existRoleStatuses);
    }
}
