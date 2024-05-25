package com.example.nacos1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/25 16:11
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/order")
    public String order() {
        System.out.println("=================order================");
        return "order";
    }
}
