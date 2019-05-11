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
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mi&Jack
 */
public class AbstractParameterMap<T> implements Serializable {
    private static final long serialVersionUID = -5678465278227489520L;
    Map<String, T> map = new HashMap<>();

    public T getParameter(String key) {
        return getParameter(key, (T) null);
    }


    public T getParameter(String key, T defaultValue) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return defaultValue;
    }

    public void setParameter(String key, T value) {
        map.put(key, value);
    }
}
