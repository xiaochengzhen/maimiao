package com.example.lamdademo.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/23 16:50
 */
public class Student {
    private String name;

    public String getName() {
        return "xxx";
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Student student = new Student();
        Function<? super Student, ?> ssss = Student::getName;
        Object apply = ssss.apply(student);
        Function<? super Student, ?> ssss1 = (student1)->"abc";
        Object apply1 = ssss1.apply(student);

        System.out.println(apply);
        Test2 getName = student::getName;
        String s = getName.testString();
        System.out.println(s);
        List<Student> list = new ArrayList<>();
        list.stream().map(ssss).findFirst();
    }
}
