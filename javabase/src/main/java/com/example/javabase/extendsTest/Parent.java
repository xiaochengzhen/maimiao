package com.example.javabase.extendsTest;

import lombok.Data;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/12 13:48
 */
@Data
public class Parent {

    private String name;
    private int age;

    public Parent() {
    }

    public Parent(String name) {
        this.name = name;
    }
}
