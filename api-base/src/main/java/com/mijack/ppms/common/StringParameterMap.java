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

package com.mijack.ppms.common;


import java.io.Serializable;

/**
 * @author Mi&Jack
 */
public class StringParameterMap extends AbstractParameterMap<String> implements Serializable {
    public <R> R getParameter(String key, Class<R> clazz) {
        return getParameter(key, null, clazz);
    }

    private <R> R getParameter(String key, String defaultValue, Class<R> clazz) {
        String value = getParameter(key, defaultValue);
        if (value == null) {
            return null;
        }
        if (String.class.equals(clazz)) {
            return (R) value;
        }
        if (clazz.isPrimitive()) {
            if (int.class == clazz || Integer.class == clazz) {
                return (R) Integer.valueOf(value);
            }
            if (double.class == clazz || Double.class == clazz) {
                return (R) Double.valueOf(value);
            }
            if (float.class == clazz || Float.class == clazz) {
                return (R) Float.valueOf(value);
            }
            if (boolean.class == clazz || Boolean.class == clazz) {
                return (R) Boolean.valueOf(value);
            }
            if (void.class == clazz || Void.class == clazz) {
                return null;
            }
            if (short.class == clazz || Short.class == clazz) {
                return (R) Short.valueOf(value);
            }
            if (byte.class == clazz || Byte.class == clazz) {
                return (R) Byte.valueOf(value);
            }
            if (long.class == clazz || Long.class == clazz) {
                return (R) Long.valueOf(value);
            }
            if (char.class == clazz || Character.class == clazz) {
                if (value == null || value.length() == 0) {
                    return null;
                }
                if (value.length() == 1) {
                    R r = (R) Character.valueOf(value.charAt(0));
                    return r;
                }
            }


        }
        throw new IllegalStateException("Class " + clazz + " 不支持相关操作");
    }

}
