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

package com.mijack.ppms.user.aop;

import com.google.common.collect.Lists;
import com.mijack.ppms.api.IParameterChecker;
import com.mijack.ppms.user.annotations.UserRequire;
import com.mijack.ppms.user.dto.CheckModel;
import com.mijack.ppms.user.dto.Role;
import com.mijack.ppms.user.dto.UserRequirement;
import com.mijack.ppms.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mi&Jack
 */
@Component
//@Aspect
public class UserIdChecker implements IParameterChecker<Long, UserRequire> {
    @Autowired
    private UserManager userManager;

    // see https://stackoverflow.com/questions/10247116/spring-aop-pointcut-for-annotated-argument
//    @Before("execution(* *(@CustomAnnotation (*), ..)) || " +
//            "execution(* *(.., @CustomAnnotation (*), ..)) || " +
//            "execution(* *(.., @CustomAnnotation (*)))")
    @Override
    public void doParameterCheck(Long userId, UserRequire userRequire) {
        boolean requireExists = userRequire.requireExists();
        boolean requireValid = userRequire.requireValid();
        List<Role> checkRoles = Lists.newArrayList(userRequire.checkRoles());
        CheckModel checkModel = userRequire.checkModel();
        UserRequirement userRequirement = new UserRequirement(userId, requireExists, requireValid, checkRoles, checkModel);
        userManager.checkUserRequirement(userRequirement);
    }
}
