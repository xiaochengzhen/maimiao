package com.example.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/14 14:02
 */
@Controller
public class TestController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
