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

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mi&Jack
 */
@Data
public class UserStatus implements Serializable {
    private static final long serialVersionUID = 5452775627986235981L;

    public static final int UNKNOWN_STATUS = -1;
    public static final int INIT_STATUS = 1;
    public static final int DELETE_STATUS = 2;
    public static final int LOCKED_STATUS = 3;

    private long userId;
    private int status = UNKNOWN_STATUS;

    public boolean isDeleted() {
        return status == DELETE_STATUS;
    }

    public boolean isLocked() {
        return status == LOCKED_STATUS;
    }
}
