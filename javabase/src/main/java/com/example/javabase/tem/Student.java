package com.example.javabase.tem;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Student extends StudentTem{
    @Override
    protected void doSomething() {
        System.out.println("play");
    }


}
