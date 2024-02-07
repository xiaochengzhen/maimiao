package com.example.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StudyApplication {

    @Autowired
    private Test test;
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

    @PostConstruct
    public void test() {
        String a = test.getA();
        System.out.println(a);
    }

}
