package com.example.springsecurity.config;

import com.example.springsecurity.hander.MyAccessDeniedHandler;
import com.example.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 08:38
 */
@Configuration
public class WebSecurityCofig1 extends WebSecurityConfigurerAdapter {

    @Lazy
    @Autowired
    private SysUserService sysUserService;


    @Bean
    public PasswordEncoder passwordEncoder() {
       // return new BCryptPasswordEncoder();
        return  NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeHttpRequests()
                .antMatchers("/admin/demo").permitAll()//不认证
            //    .antMatchers("/admin/demo").hasAuthority("user")//要有user权限
           //     .antMatchers("admin/demo").hasRole("user")//要有user角色
                .anyRequest().authenticated();//所有请求都要认证

        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        http.csrf().disable();//去除 csrf



    }

}
