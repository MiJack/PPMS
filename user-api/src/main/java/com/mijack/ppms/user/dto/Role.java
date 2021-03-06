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

import com.mijack.ppms.enums.IdentifierEnum;

/**
 * @author Mi&Jack
 */
public enum Role implements IdentifierEnum<Role> {
    /**
     * 超级管理员
     */
    ROOT(0, "超级管理员"),
    /**
     * 管理员
     */
    ADMIN(1, "管理员"),
    /**
     * 用户
     */
    USER(2, "用户"),
    /**
     * 未知用户
     */
    UNKNOWN(-1, "未知用户");

    private static final String ROLE_PREFIX = "ROLE_";
    /**
     * id
     */
    private int id;
    /**
     * 角色描述
     */
    private String description;

    Role(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public int id() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}