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

import com.mijack.ppms.api.RpcResult;

import java.text.MessageFormat;

/**
 * @author Mi&Jack
 */
public interface IRpcCodeEnum extends RpcExceptionDesc, IToRpcException {
    /**
     * 通过 MessageFormat.format解析消息的格式化，并生成对应的Exception
     *
     * @param objects 异常的消息模板的参数
     * @return
     * @see java.text.MessageFormat#format(String, Object...)
     */
    default RpcException toException(Object... objects) {
        return new RpcException(code(), MessageFormat.format(msg(), objects));
    }

    /**
     * 通过 MessageFormat.format解析消息的格式化，并抛出对应的Exception
     *
     * @param objects 异常的消息模板的参数
     * @return
     * @see java.text.MessageFormat#format(String, Object...)
     */
    default void throwException(Object... objects) {
        throw toException(code(), msg(), objects);
    }

    /**
     * 通过 MessageFormat.format解析消息的格式化，并生成对应的RpcResult
     *
     * @param result  rpc调用的结果
     * @param objects rpc异常的消息模板的参数
     * @param <T>     rpc调用的结果的类型
     * @return
     * @see java.text.MessageFormat#format(String, Object...)
     */
    default <T> RpcResult<T> wrapResult(T result, Object... objects) {
        RpcResult<T> rpcResult = new RpcResult<>(code(), MessageFormat.format(msg(), objects), result);
        return rpcResult;
    }

    /**
     * rpc异常的类型
     *
     * @return
     * @see RpcExceptionDesc#errorType()
     */
    @Override
    default String errorType() {
        return name();
    }

    /**
     * rpc异常的名称
     *
     * @return
     * @see com.mijack.ppms.exceptions.IRpcCodeEnum#name()
     */
    String name();
}
