package com.example.javabase.design.factory.factory1;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 10:05
 */
public class PersonFactory {

    public Person createFactory(String type) {
        if ("student".equals(type)){
            return new Student();
        } else if ("teacher".equals(type)){
            return new Teacher();
        }
        return null;
    }
}
