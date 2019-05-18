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

import com.google.common.collect.Sets;
import com.mijack.ppms.exceptions.ServerErrorException;
import com.mijack.ppms.user.dto.CheckModel;
import com.mijack.ppms.user.dto.Role;
import com.mijack.ppms.user.dto.UserRequirement;
import com.mijack.ppms.user.dto.UserStatus;
import com.mijack.ppms.user.exceptions.NoAllUserRoleFoundException;
import com.mijack.ppms.user.exceptions.NoAnyUserRoleFoundException;
import com.mijack.ppms.user.exceptions.NoUserFoundException;
import com.mijack.ppms.user.exceptions.UserLockedException;
import com.mijack.ppms.user.manager.UserManager;
import com.mijack.ppms.user.manager.UserRoleManager;
import com.mijack.ppms.user.manager.UserStatusManager;
import com.mijack.ppms.utils.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Mi&Jack
 */
@Component
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserStatusManager userStatusManager;
    @Autowired
    private UserRoleManager userRoleManager;

    /**
     * 检查userRequirement中的用户角色状态
     *
     * @param userRequirement
     */
    public void checkUserRequirement(UserRequirement userRequirement) {
        long userId = userRequirement.getUserId();
        UserStatus userStatus = userStatusManager.findUserStatus(userId);
        if (userStatus == null || userStatus.isDeleted()) {
            if (userRequirement.isRequireExists()) {
                throw new NoUserFoundException(userId);
            } else {
                return;
            }
        }
        if (userStatus.isLocked()) {
            if (userRequirement.isRequireValid()) {
                throw new UserLockedException(userId);
            } else {
                return;
            }
        }

        Set<Role> checkRoles = Sets.newHashSet(userRequirement.getCheckRoles());
        if (CollectionHelper.isEmpty(checkRoles)) {
            // nop
            return;
        }
        CheckModel checkModel = userRequirement.getCheckModel();
        Set<Role> userRoles = Sets.newHashSet(userRoleManager.listUserRole(userId));

        switch (checkModel) {
            case HAVE_ANY_ROLE:
                for (Role checkRole : checkRoles) {
                    if (userRoles.contains(checkRole)) {
                        return;
                    }
                }
                throw new NoAnyUserRoleFoundException(userId, checkRoles);
            case HAVE_ALL_ROLES:
                for (Role checkRole : checkRoles) {
                    if (!userRoles.contains(checkRole)) {
                        throw new NoAllUserRoleFoundException(userId, checkRoles);
                    }
                }
                return;
            default:
                throw new ServerErrorException("CheckModel = null");
        }
    }
}
