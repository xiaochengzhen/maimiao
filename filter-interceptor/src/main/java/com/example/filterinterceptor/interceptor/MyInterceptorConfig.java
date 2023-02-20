package com.example.filterinterceptor.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/4 15:22
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    //可以配置多个拦截器，按配置的顺序执行
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }
}
