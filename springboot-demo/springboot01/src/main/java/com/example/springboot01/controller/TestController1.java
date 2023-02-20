package com.example.springboot01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/15 13:24
 */
@RestController
@RequestMapping("/test")
public class TestController1 {

    @RequestMapping("/test1")
    public String test1() {
        return "hello springboot";
    }
}
