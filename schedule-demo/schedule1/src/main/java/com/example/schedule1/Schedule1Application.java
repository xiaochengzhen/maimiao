package com.example.schedule1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Schedule1Application {

    public static void main(String[] args) {
        SpringApplication.run(Schedule1Application.class, args);
    }

}
