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

package com.mijack.ppms.portal.config;

import com.mijack.ppms.portal.security.LoginAuthenticationSuccessHandler;
import com.mijack.ppms.portal.security.ProtalMethodSecurityExpressionHandler;
import com.mijack.ppms.portal.security.RestfulApiAuthenticationProcessingFilter;
import com.mijack.ppms.portal.service.ProtalUserService;
import com.mijack.ppms.user.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mi&Jack
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String REMEMBER_ME_PARAMETER = "remember-me";
    @Autowired
    ProtalUserService protalUserService;
    @Autowired
    LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    @Autowired
    AuthenticationProvider restfulApiAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) {
        web.expressionHandler(securityExpressionHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(restfulApiAuthenticationProvider)
                .userDetailsService(protalUserService)
                // todo 接入user-api和token api
//                .passwordEncoder(passwordEncoder())
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //api接口不做csrf处理
        http.csrf().ignoringAntMatchers("/api/**");


        http.authorizeRequests()
                .antMatchers("/**")
                .access("!hasRole('" + Role.ADMIN.name() + "')" +
                        " or isLocalhost()")
                .expressionHandler(securityExpressionHandler())
                // css、js可任意访问
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/js/**")
                .permitAll()
                // 404 可访问
                .regexMatchers("/404.html")
                .permitAll()
        ;


        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login.html?error")
                .successHandler(loginAuthenticationSuccessHandler)
                .usernameParameter("user-name")
                .passwordParameter("password")
                .authenticationDetailsSource(request -> {
                    Map<String, String> map = new HashMap<>(16);
                    map.put("username", request.getParameter("username"));
                    map.put("email", request.getParameter("email"));
                    map.put("password", request.getParameter("password"));
                    return map;
                })
                .and()
                .rememberMe().key(REMEMBER_ME_PARAMETER)
                .rememberMeParameter(REMEMBER_ME_PARAMETER)
                // todo 记住我的token 接入token api
//                .tokenRepository(tokenRepository())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        ;

        http.addFilterBefore(restfulApiAuthenticationProcessingFilter(authenticationManager()), BasicAuthenticationFilter.class);

    }


    @Bean
    public RestfulApiAuthenticationProcessingFilter restfulApiAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager) {
        RestfulApiAuthenticationProcessingFilter restfulApiAuthenticationProcessingFilter
                = new RestfulApiAuthenticationProcessingFilter();
        restfulApiAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        return restfulApiAuthenticationProcessingFilter;
    }

    @Bean
    public ProtalMethodSecurityExpressionHandler securityExpressionHandler() {
        return new ProtalMethodSecurityExpressionHandler();
    }

}
