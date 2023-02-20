package com.example.springsecurity.config;

import com.example.springsecurity.hander.MyAccessDeniedHandler;
import com.example.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
      //  return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
       // .loginPage("/login.html") //自定义登录页面
      //  .loginProcessingUrl("/login") //登录访问路径，必须和表单提交接口一样
        .successForwardUrl("/loginSuccess") //认证成功之后转发的路径,必须是Post请求
        .failureForwardUrl("/loginError"); //认证失败之后转发的路径,必须是Post请
        http.authorizeRequests()
                .antMatchers("/test").hasAnyAuthority("admin","user")//无效
             //   .antMatchers("/test").hasAnyAuthority("user")//有效
                .antMatchers("/create").permitAll()//不认证
                .anyRequest().authenticated();//所有请求都要认证
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        http.csrf().disable();//去除 csrf
    }
}
