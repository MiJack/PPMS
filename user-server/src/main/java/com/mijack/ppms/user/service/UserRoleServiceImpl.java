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

package com.mijack.ppms.user.service;

import com.google.common.collect.Lists;
import com.mijack.ppms.api.RpcResult;
import com.mijack.ppms.common.CollectionHelper;
import com.mijack.ppms.enums.Enums;
import com.mijack.ppms.user.annotations.UserRequire;
import com.mijack.ppms.user.api.UserRoleService;
import com.mijack.ppms.user.dto.Role;
import com.mijack.ppms.user.dto.RoleStatus;
import com.mijack.ppms.user.dto.UserRpcCode;
import com.mijack.ppms.user.dto.UserStatus;
import com.mijack.ppms.user.manager.UserRoleManager;
import com.mijack.ppms.user.manager.UserStatusManager;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mi&Jack
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleManager userRoleManager;
    @Autowired
    private UserStatusManager userStatusManager;

    @Override
    public RpcResult<List<RoleStatus>> listUserRole(@UserRequire long userId) {
        checkUserStatus(userId);
        List<RoleStatus> roles = userRoleManager.listAllUserRole(userId);
        return UserRpcCode.ResultOk.wrapResult(roles);
    }

    private void checkUserStatus(long userId) {
        UserStatus userStatus = userStatusManager.findUserStatus(userId);
        if (userStatus == null || userStatus.isDeleted()) {
            throw UserRpcCode.NoUserFound.toException(userId);
        }
        if (userStatus.isLocked()) {
            throw UserRpcCode.UserLocked.toException(userId);
        }
    }

    @Override
    public RpcResult<Boolean> hasAnyRole(@UserRequire long userId, List<Role> roles) {
        List<Role> userRoles = userRoleManager.listUserRole(userId);
        if (CollectionHelper.isEmpty(userRoles)) {
            return UserRpcCode.NO_USER_ROLE.wrapResult(false, userId);
        }
        if (CollectionHelper.isEmpty(roles)) {
            return UserRpcCode.WRONG_PARAM.wrapResult(false);
        }
        for (Role role : roles) {
            if (userRoles.contains(role)) {
                return UserRpcCode.ResultOk.wrapResult(true);
            }
        }
        return UserRpcCode.ResultOk.wrapResult(false);
    }

    @Override
    public RpcResult<Boolean> hasAllRole(@UserRequire long userId, List<Role> roles) {
        List<Role> userRoles = userRoleManager.listUserRole(userId);
        if (CollectionHelper.isEmpty(userRoles)) {
            return UserRpcCode.NO_USER_ROLE.wrapResult(false, userId);
        }
        if (CollectionHelper.isEmpty(roles)) {
            return UserRpcCode.WRONG_PARAM.wrapResult(false);
        }
        for (Role role : roles) {
            if (!userRoles.contains(role)) {
                return UserRpcCode.ResultOk.wrapResult(false);
            }
        }
        return UserRpcCode.ResultOk.wrapResult(true);
    }

    @Override
    public RpcResult<Boolean> addRoles(@UserRequire long userId,
                                       @UserRequire(checkRoles = Role.ADMIN) long operatorId, List<Role> roles) {
        List<RoleStatus> roleStatuses = userRoleManager.listAllUserRole(userId);
        List<RoleStatus> existRoleStatuses = Lists.newArrayList();
        List<Role> createRoles = Lists.newArrayList(roles);
        for (RoleStatus roleStatus : roleStatuses) {
            Role role = Enums.idOf(Role.class, roleStatus.getRoleId());
            if (roles.contains(role)) {
                roleStatus.setStatus(RoleStatus.STATUS_ENABLE);
                existRoleStatuses.add(roleStatus);
                createRoles.remove(role);
            }
        }
        userRoleManager.updateUserRoles(existRoleStatuses);
        userRoleManager.createUserRoles(createRoles.stream()
                .map(role -> RoleStatus.builder().roleId(role.id()).status(RoleStatus.STATUS_ENABLE).build())
                .collect(Collectors.toList()));
        return UserRpcCode.ResultOk.wrapResult(true);
    }

    @Override
    public RpcResult<Boolean> disableRoles(@UserRequire long userId,
                                           @UserRequire(checkRoles = Role.ADMIN) long operatorId, List<Role> roles) {
        List<RoleStatus> roleStatuses = userRoleManager.listAllUserRole(userId);
        List<RoleStatus> existRoleStatuses = Lists.newArrayList();
        List<Role> createRoles = Lists.newArrayList(roles);
        for (RoleStatus roleStatus : roleStatuses) {
            Role role = Enums.idOf(Role.class, roleStatus.getRoleId());
            if (roles.contains(role)) {
                roleStatus.setStatus(RoleStatus.STATUS_DISABLE);
                existRoleStatuses.add(roleStatus);
                createRoles.remove(role);
            }
        }
        userRoleManager.updateUserRoles(existRoleStatuses);
        userRoleManager.createUserRoles(createRoles.stream()
                .map(role -> RoleStatus.builder().roleId(role.id()).status(RoleStatus.STATUS_DISABLE).build())
                .collect(Collectors.toList()));
        return UserRpcCode.ResultOk.wrapResult(true);
    }

    @Override
    public RpcResult<Boolean> removeRoles(@UserRequire long userId,
                                          @UserRequire(checkRoles = Role.ADMIN) long operatorId, List<Role> roles) {
        List<RoleStatus> roleStatuses = userRoleManager.listAllUserRole(userId);
        List<RoleStatus> existRoleStatuses = Lists.newArrayList();
        for (RoleStatus roleStatus : roleStatuses) {
            Role role = Enums.idOf(Role.class, roleStatus.getRoleId());
            if (roles.contains(role)) {
                existRoleStatuses.add(roleStatus);
            }
        }
        userRoleManager.removeUserStatus(existRoleStatuses);
        return UserRpcCode.ResultOk.wrapResult(true);
    }
}
