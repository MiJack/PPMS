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

package com.mijack.ppms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mi&Jack
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequirement implements Serializable {

    private static final long serialVersionUID = 657243623242599855L;
    /**
     * 待检查的用户id
     */
    private long userId;
    /**
     * 要求userId对应的用户存在
     *
     * @return
     */
    private boolean requireExists;

    /**
     * 要求userId对应的用户处于启用状态
     *
     * @return
     */
    private boolean requireValid;

    /**
     * 待检查用户的用户角色
     *
     * @return
     */
    private List<Role> checkRoles;

    /**
     * 对待检测用户的角色的要求:
     * <ul>
     * <li>当checkModel为HAVE_ALL_ROLES时，表示要求userId持有所有的用户角色</li>
     * <li>当checkModel为HAVE_ANY_ROLE时，表示要求userId持有部分的用户角色</li>
     * </ul>
     *
     * @return
     */
    private CheckModel checkModel;
}
