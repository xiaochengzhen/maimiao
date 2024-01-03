package com.example.redis1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class Redis1Application {

    public static void main(String[] args) {
        SpringApplication.run(Redis1Application.class, args);
    }

}
