package com.example.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/14 10:41
 */
@ConfigurationProperties("spring.start")
public class Properies {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
