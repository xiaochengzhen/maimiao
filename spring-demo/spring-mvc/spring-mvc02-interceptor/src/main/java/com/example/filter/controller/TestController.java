package com.example.filter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/4 10:36
 */
@RestController
public class TestController {
    @RequestMapping("/test1")
    public String test1(String name) {
        System.out.println("==============test1================");
        return name;
    }
}
