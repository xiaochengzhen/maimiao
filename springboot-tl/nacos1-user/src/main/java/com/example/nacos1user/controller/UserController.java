package com.example.nacos1user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/25 16:11
 */
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/user")
    public void user() {
        System.out.println("=================user================");
        ServiceInstance choose = loadBalancerClient.choose("nacos1-order");
        String host = choose.getHost();
        int port = choose.getPort();
        restTemplate.getForObject(String.format("http://%s:%s/order", host, port), Object.class);
    }

    @RequestMapping("/user1")
    public void user1() {
        System.out.println("=================user================");
        restTemplate.getForObject("http://nacos1-order/order", Object.class);
    }
}
