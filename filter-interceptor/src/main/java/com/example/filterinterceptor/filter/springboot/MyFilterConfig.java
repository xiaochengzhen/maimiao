package com.example.filterinterceptor.filter.springboot;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author xiaobo
 * @description  顺序可以通过配置order 控制，如果没有，按照fiter 的名字字母顺序执行   abc
 * @date 2022/8/4 14:34
 */
@Configuration
public class MyFilterConfig {
    //这种方式向容器中添加过滤器可以实现拦截路径定义和优先级
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SpringbootFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }


    //可以配置多个
    @Bean
    public FilterRegistrationBean filterRegistration2() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SpringbootFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }

    //可以直接向容器中放入过滤  直接向容器中添加过滤器，无法控制拦截路径和优先级
    @Bean
    public Filter filter() {
        return new SpringbootFilter();
    }
}
