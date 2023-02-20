package com.example.springboot03.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/17 09:38
 */
@Profile("prod")
@RestController
public class TestController1 {

    @RequestMapping("/test11")
    public String test1() {
        return "prod";
    }
}
