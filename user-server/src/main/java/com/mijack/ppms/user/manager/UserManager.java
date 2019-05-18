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

package com.mijack.ppms.user.manager;

import com.mijack.ppms.user.dto.UserRequirement;

/**
 * @author Mi&Jack
 */
public interface UserManager {
    /**
     * 检查userRequirement中的用户角色状态
     *
     * @param userRequirement
     */
    void checkUserRequirement(UserRequirement userRequirement);
}
