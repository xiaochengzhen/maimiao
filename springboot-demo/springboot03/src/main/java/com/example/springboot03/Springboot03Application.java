package com.example.springboot03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*
* 优先级从高到低
* 1、默认的application.properties
* 2、通过@PropertySource, 只能配置properties
* 3、通过 启动类中set获取的  只能配置properties
*
*
* */
@PropertySource("classpath:application-dev.properties")
//@PropertySource("classpath:application-dev.yml")
@SpringBootApplication
public class Springboot03Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Springboot03Application.class);
        Properties propertySource = new Properties();
        InputStream resourceAsStream = Springboot03Application.class.getClassLoader().getResourceAsStream("application-prod.properties");
        try {
            propertySource.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        springApplication.setDefaultProperties(propertySource);
        springApplication.run(args);
    }

}
