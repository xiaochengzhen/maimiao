package com.example.springbootaop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan(basePackages = "com.example.springbootaop.mapper")
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringbootAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopApplication.class, args);
    }

}
