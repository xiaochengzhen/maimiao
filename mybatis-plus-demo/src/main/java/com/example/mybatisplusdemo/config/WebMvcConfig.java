package com.example.mybatisplusdemo.config;
/**
 * @description
 * @author xiaobo
 * @date 2023/4/24 17:08
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @description
 * @author xiaobo
 * @date 2023/4/24 17:08
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2Ct = new MappingJackson2HttpMessageConverter();
        converters.add(0, jackson2Ct);
    }
}
