package com.example.springbootintercept;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.example.springbootintercept.mapper")
@SpringBootApplication
public class SpringbootInterceptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInterceptApplication.class, args);
    }

}
