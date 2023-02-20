package com.example.springmvc01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/28 11:55
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String test1(String name) {
        System.out.println("请求参数："+name);
        /*
        * redirect  重定向
        * forwark  转发  默认
        * */
        return "redirect:index.jsp";
    }

    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public String header(@RequestHeader("Host") String Host) {
        System.out.println("请求参数："+Host);
        return "redirect:index.jsp";
    }

    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
    public String cooke(@CookieValue("JSESSIONID") String cookie) {
        System.out.println("请求参数："+cookie);
        return "redirect:index.jsp";
    }
}
