package com.example.config;

import com.example.controller.TestController;
import com.example.model.Properies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/14 10:58
 */
@Configuration
@ConditionalOnProperty("spring.start.name")
@EnableConfigurationProperties(Properies.class)
public class TestConfig {

    @Autowired
    private Properies properies;
    @Bean
    public TestController testController() {
        return new TestController(properies);
    }
}
