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

import com.mijack.ppms.exceptions.IRpcCodeEnum;

/**
 * @author Mi&Jack
 */
public enum UserRpcCode implements IRpcCodeEnum {
    /**
     * 调用成功
     */
    ResultOk(0, "调用成功"),
    /**
     * 用户未找到
     */
    NoUserFound(1, "用户（userId = {1}）未找到"),
    /**
     * 用户已锁定
     */
    UserLocked(2, "用户（userId = {1}）已锁定"),
    /**
     * 用户无权限
     */
    NO_USER_ROLE(3, "用户（userId = {1}）无权限"),
    /**
     * 非法参数
     */
    WRONG_PARAM(4,"非法参数")
    ;
    private int code;
    private String msg;

    UserRpcCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
