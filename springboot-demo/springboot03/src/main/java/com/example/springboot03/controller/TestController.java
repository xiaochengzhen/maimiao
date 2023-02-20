package com.example.springboot03.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/17 09:38
 */
@Profile("dev")//通过启动加载配置文件来加载bean
@RestController
public class TestController {

    @RequestMapping("/test1")
    public String test1() {
        return "dev1121";
    }
}
