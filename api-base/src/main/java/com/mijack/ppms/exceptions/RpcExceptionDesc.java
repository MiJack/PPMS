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

/**
 * @author Mi&Jack
 */
public interface RpcExceptionDesc {
    /**
     * 对应rpc调用异常的结果
     *
     * @return
     */
    int code();

    /**
     * 对应rpc调用异常的消息
     *
     * @return
     */
    String msg();

    /**
     * 对应rpc调用异常的类型
     *
     * @return
     */
    String errorType();
}
