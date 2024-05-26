package com.example.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@SpringBootApplication
public class Springboot2Application {

    public static void main(String[] args) {

        SpringApplication.run(Springboot2Application.class, args);
    }

}
