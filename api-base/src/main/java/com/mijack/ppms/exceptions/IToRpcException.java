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

import java.text.MessageFormat;

/**
 * @author Mi&Jack
 */
public interface IToRpcException {
    /**
     * 通过 MessageFormat.format解析消息的格式化，并生成对应的Exception
     *
     * @param code    异常的错误代码
     * @param msg     异常的消息模板
     * @param objects 异常的消息模板的参数
     * @return
     * @see java.text.MessageFormat#format(String, Object...)
     */
    default RpcException toException(int code, String msg, Object... objects) {
        return new RpcException(code, MessageFormat.format(msg, objects));
    }

    /**
     * 通过 MessageFormat.format解析消息的格式化，并抛出对应的Exception
     *
     * @param code    异常的错误代码
     * @param msg     异常的消息模板
     * @param objects 异常的消息模板的参数
     * @return
     * @see java.text.MessageFormat#format(String, Object...)
     */
    default void throwException(int code, String msg, Object... objects) {
        throw toException(code, msg, objects);
    }
}
