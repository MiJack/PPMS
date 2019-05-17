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

package com.mijack.ppms.user.annotations;

import com.mijack.ppms.user.dto.CheckModel;
import com.mijack.ppms.user.dto.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mi&Jack
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRequire {
    /**
     * 要求userId对应的用户存在
     *
     * @return
     */
    boolean exists() default true;

    /**
     * 要求userId对应的用户处于启用状态
     *
     * @return
     */
    boolean valid() default true;

    /**
     * 待检查用户的用户角色
     *
     * @return
     */
    Role[] checkRoles() default {};

    /**
     * 对待检测用户的角色的要求:
     * <ul>
     * <li>当checkModel为HAVE_ALL_ROLES时，表示要求userId持有所有的用户角色</li>
     * <li>当checkModel为HAVE_ANY_ROLE时，表示要求userId持有部分的用户角色</li>
     * </ul>
     *
     * @return
     * @see #checkModel()
     */
    CheckModel checkModel() default CheckModel.HAVE_ALL_ROLES;

}
