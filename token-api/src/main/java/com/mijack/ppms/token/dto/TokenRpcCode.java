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

import com.mijack.ppms.exceptions.IRpcCodeEnum;

/**
 * @author Mi&Jack
 */
public enum TokenRpcCode implements IRpcCodeEnum {
    /**
     * TokenType不支持
     */
    TokenTypeNotSupported(1, "TokenType(name = {0})不支持"),
    /**
     *TokenTypeCode不支持
     */
    TokenTypeCodeNotSupported(2, "TokenTypeCode(code = {0})不支持"),
    /**
     *token生成失败
     */
    TokenGenerationFailed(3, "token生成失败"),
    /**
     *Token已过期
     */
    TokenInvaild(4, "Token已过期"),
    /**
     *逆向解析token操作不支持
     */
    ReverseTokenNotSupported(5, "TokenTypeCode(code = {0})逆向解析token操作不支持"),
    /**
     * 延长token操作不支持
     */
    ExtendTokenExpireTimeNotSupported(6, "TokenTypeCode(code = {0})延长token操作不支持"),
    /**
     * 强制token失效操作不支持
     */
    InvaildTokenNotSupported(7, "TokenTypeCode(code = {0})强制token失效操作不支持"),
    /**
     * 参数格式异常
     */
    ParameterError(8, "参数格式异常:{0} = {1}"),
    /**
     * token格式异常
     */
    TokenError(9, "token格式异常"),
    ;

    private int code;
    private String msg;

    TokenRpcCode(int code, String msg) {
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
