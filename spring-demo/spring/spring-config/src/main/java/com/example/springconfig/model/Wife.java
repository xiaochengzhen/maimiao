package com.example.springconfig.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/24 09:58
 */
public class Wife {
    @Value("wode")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
