package com.example.nacos1user;

import com.example.nacos1user.config.CustomLoadBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@LoadBalancerClient(value = "nacos1-order", configuration = CustomLoadBalancerConfiguration.class)
public class Nacos1UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(Nacos1UserApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate build = new RestTemplateBuilder().build();
        return build;
    }

}
