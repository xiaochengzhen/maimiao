package com.example.nacos1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
public class Nacos1Application {

    public static void main(String[] args) {
        SpringApplication.run(Nacos1Application.class, args);
    }

}
