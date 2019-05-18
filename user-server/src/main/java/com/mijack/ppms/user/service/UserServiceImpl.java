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

import com.mijack.ppms.api.RpcCode;
import com.mijack.ppms.api.RpcResult;
import com.mijack.ppms.user.api.UserService;
import com.mijack.ppms.user.dto.UserRequirement;
import com.mijack.ppms.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Mi&Jack
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserManager userManager;

    /**
     * 检查userRequirement中的用户角色状态
     *
     * @param userRequirement
     * @return
     */
    @Override
    public RpcResult<Void> checkUserRequirement(UserRequirement userRequirement) {
        userManager.checkUserRequirement(userRequirement);
        return RpcCode.ResultOk.wrapResult(null);
    }
}
