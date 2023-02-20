package com.example.springboot02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/15 20:21
 */
@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }
}
