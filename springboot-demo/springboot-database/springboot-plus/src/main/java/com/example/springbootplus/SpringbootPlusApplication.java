package com.example.springbootplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.example.springbootplus.mapper")
@SpringBootApplication
public class SpringbootPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPlusApplication.class, args);
    }

}
