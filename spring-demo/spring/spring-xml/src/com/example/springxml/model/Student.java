package com.example.springxml.model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/22 08:43
 */
public class Student implements InitializingBean, DisposableBean {
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

    public static Student getStudent() {
        Student student = new Student();
        student.setAge(100);
        return student;
    }

    public void initMeoth() {
        System.out.println("xml");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("impl");
    }

    @Override
    public void destroy() throws Exception {

    }
}
