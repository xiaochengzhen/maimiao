package com.example.springannotionxml.model;

import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/23 09:31
 */
@Component
public class User {
    private String name;
    private Integer age;

    public User() {
        System.out.println("user 已加载");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
