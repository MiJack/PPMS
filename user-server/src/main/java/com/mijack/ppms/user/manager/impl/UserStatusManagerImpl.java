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

import com.mijack.ppms.user.dao.UserStatusRepository;
import com.mijack.ppms.user.dto.UserStatus;
import com.mijack.ppms.user.manager.UserStatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mi&Jack
 */
@Component
public class UserStatusManagerImpl implements UserStatusManager {
    @Autowired
    private UserStatusRepository userStatusRepository;
    /**
     * 根据userId查询得到对应的UserStatus
     *
     * @param userId
     * @return
     */
    @Override
    public UserStatus findUserStatus(long userId) {
        return userStatusRepository.findUserStatus(userId);
    }
}
