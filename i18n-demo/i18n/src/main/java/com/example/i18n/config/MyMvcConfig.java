package com.example.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/14 14:40
 */

//@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //返回/abc会跳转到test.html
       /* registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");*/
    }
    //注入到Bean
   /* @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
*/
}
