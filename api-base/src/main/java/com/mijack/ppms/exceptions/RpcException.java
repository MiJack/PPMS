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

package com.mijack.ppms.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mi&Jack
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RpcException extends RuntimeException implements RpcExceptionDesc {
    private int code;
    private String msg;
    private String errorType;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public RpcException(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.errorType = "";
    }

    public RpcException(RpcExceptionDesc rpcExceptionDesc) {
        this.code = rpcExceptionDesc.code();
        this.msg = rpcExceptionDesc.msg();
        this.errorType = rpcExceptionDesc.errorType();
    }

    /**
     * 对应rpc调用异常的结果
     *
     * @return
     */
    @Override
    public int code() {
        return code;
    }

    /**
     * 对应rpc调用异常的消息
     *
     * @return
     */
    @Override
    public String msg() {
        return msg;
    }

    /**
     * 对应rpc调用异常的类型
     *
     * @return
     */
    @Override
    public String errorType() {
        return errorType;
    }
}
