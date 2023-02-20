package com.example.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 08:15
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/index")
    public String index() {
        String username = getUsername();
        return "欢迎登录："+username;
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = "";
        if (principal != null) {
            if (principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                username = userDetails.getUsername();
            } else {
                username = principal.toString();
            }
        }
        return username;
    }

    @GetMapping("/admin/demo")
    public String demo() {
        return "/admin/demo";
    }

}
