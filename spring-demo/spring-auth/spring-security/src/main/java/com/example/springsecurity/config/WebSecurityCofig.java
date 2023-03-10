package com.example.springsecurity.config;

import com.example.springsecurity.MyUserDetailsService;
import com.example.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
//@Configuration
//@EnableWebSecurity
public class WebSecurityCofig extends WebSecurityConfigurerAdapter {
   /* @Autowired
    private UserDetailsService myUserDetailsService;*/

    @Autowired
    private SysUserService sysUserService;
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      *//* auth.inMemoryAuthentication()
               .withUser("x")
               .password(passwordEncoder().encode("123456"))
               .authorities("admin")
               .and()
               .withUser("x1")
               .password(passwordEncoder().encode("123456"))
               .authorities("admin");
*//*
        auth.userDetailsService(myUserDetailsService);

    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
      //  return new BCryptPasswordEncoder();
        return  NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login.html")//??????????????????
                .loginProcessingUrl("/user/login")//action
                .defaultSuccessUrl("/main.html")//??????????????????
              //  .failureForwardUrl("")
                ;

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/session/invalid")//session??????
                .maximumSessions(1)//???????????????
                .expiredSessionStrategy(new MyExpiredSessionStrategy())// ??????????????????
                .maxSessionsPreventsLogin(true); //?????????????????????

        http.authorizeHttpRequests()
                .antMatchers("/login.html","/user/login", "/session/invalid").permitAll()//?????????
                .anyRequest().authenticated();//??????

        http.csrf().disable();//?????? csrf

        //?????????
        http.rememberMe()
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(3600)//?????????????????????2???
            .userDetailsService(sysUserService);//?????????????????????

    }

        @Autowired
        private DataSource dataSource;
        public PersistentTokenRepository persistentTokenRepository() {
            JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
            jdbcTokenRepository.setDataSource(dataSource);
            return jdbcTokenRepository;
        }

   /* @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new MyUserDetailsService();
    }*/




}
