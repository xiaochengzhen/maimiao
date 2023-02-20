package com.example.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 08:38
 */
@Configuration
public class WebSecurityCofig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
             //   .loginProcessingUrl("/login").permitAll()
                .and().authorizeRequests()
                .antMatchers("/oauth/**","/login","/register","/logout").permitAll()
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and().csrf().disable();

    }

    /*
    * 密码模式增加
    * */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); }

}
