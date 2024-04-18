package com.example.zookeeperdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/14 8:37
 */
@Component
@EnableAsync
@EnableWebMvc
public class TestPostProcessor implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
