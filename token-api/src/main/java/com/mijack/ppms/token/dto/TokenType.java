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

package com.mijack.ppms.token.dto;

import com.mijack.ppms.enums.DescriptionEnum;
import com.mijack.ppms.enums.IdentifierEnum;

/**
 * @author Mi&Jack
 */
public enum TokenType implements IdentifierEnum, DescriptionEnum {
    /**
     * 用户密码加密token
     */
    UserToken(0, "用户密码加密token"),
    /**
     * 用户密码加密token
     */
    UserAuthToken(1, "用户认证token");

    private int id;

    TokenType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    private String desc;

    @Override
    public int id() {
        return id;
    }

    @Override
    public String desc() {
        return desc;
    }
}
