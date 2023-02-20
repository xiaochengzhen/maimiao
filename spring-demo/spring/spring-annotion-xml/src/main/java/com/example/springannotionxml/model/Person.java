package com.example.springannotionxml.model;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/23 09:31
 */
@Component
@DependsOn(value = "user")
public class Person {
    private String name;
    private Integer age;

    public Person() {
        System.out.println("Person 已加载");
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
