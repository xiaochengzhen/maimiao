package com.example.javabase.function;

import java.util.Locale;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/10 13:10
 */
public class PrintString {

    private String name;
    private Integer age;

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

    public void printUpper(String s) {
        String result = s.toLowerCase();
        System.out.println(result);
    }
}
