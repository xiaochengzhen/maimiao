package com.example.mybatisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
@SpringBootApplication
//@SpringBootApplication(exclude = JacksonAutoConfiguration.class)
//@MapperScan(basePackages="com.example.mybatisplusdemo.mapper")
public class MybatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDemoApplication.class, args);
    }

}
