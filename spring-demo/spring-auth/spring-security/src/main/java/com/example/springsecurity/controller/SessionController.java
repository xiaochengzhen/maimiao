package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 15:34
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/invalid")
    public String invalid() {
        return "session 失效";
    }
}
