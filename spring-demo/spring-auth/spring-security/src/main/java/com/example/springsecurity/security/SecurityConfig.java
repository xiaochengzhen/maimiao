package com.example.springsecurity.security;

import com.google.common.collect.Lists;
import io.ebang.it.data.auth.db.model.menu.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.ebang.it.auth.core.security.SecurityUtils.replaceMethod;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityResource securityResource;
    /**
     * 权限配置   白名单
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        // 循环白名单进行放行
        List<String> ignoreUri = ignoredUrlsConfig().getIgnoreUri();
        if (!CollectionUtils.isEmpty(ignoreUri)) {
            ignoreUri.forEach(url -> registry.antMatchers(url).permitAll());
        }
       /* 静态资源权限*/
        List<SysMenu> sysMenus = securityResource.listSysMenuByUserId(null, null);
        if (!CollectionUtils.isEmpty(sysMenus)) {
            Map<String, List<String>> collect = sysMenus.stream().filter(sysMenu -> StringUtils.isNotBlank(sysMenu.getPath()))
                    .collect(Collectors.toMap(s -> s.getPath(), s -> Lists.newArrayList(s.getId().toString()),
                    (List<String> oldList, List<String> newList) -> {
                        oldList.addAll(newList);
                        return oldList;
                    }));
            if (!CollectionUtils.isEmpty(collect)) {
                collect.forEach((k, v)->{
                    Pair<HttpMethod, String> replace = replaceMethod(k);
                    if (replace != null) {
                        registry.antMatchers(replace.getLeft(), replace.getRight()).hasAnyAuthority(v.toArray(new String[v.size()]));
                    }
                });
            }
        }
        // 允许可以请求OPTIONS CORS
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();
        // 其他任何请求都需要身份认证
        registry.anyRequest().authenticated()
                .and().cors()
                .and().csrf()
                .disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler()).authenticationEntryPoint(restfulAuthenticationEntryPoint())
                .and().addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(paramsFilter(), LoginAuthenticationFilter.class);
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter(){
        return new LoginAuthenticationFilter();
    }

    @Bean
    public ParamsFilter paramsFilter(){
        return new ParamsFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IgnoredUrlsConfig ignoredUrlsConfig(){
        return new IgnoredUrlsConfig();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler(){
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint(){
        return new RestfulAuthenticationEntryPoint();
    }



}
