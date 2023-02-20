package com.example.springoauth2.controller;

import com.example.springoauth2.model.SysUser;
import com.example.springoauth2.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 08:15
 */
@RestController
@RequestMapping("/user")
public class TestController {

    @Resource
    private SysUserService sysUserService;
    @PostMapping("/create")
    public SysUser createSysUser(@RequestBody SysUser sysUser) {
        SysUser user = sysUserService.createUser(sysUser);
        return user;
    }

    @GetMapping("/oauth")
    public String test() {
        return "test";
    }

    @GetMapping("/index")
    public String index() {
        String username = getUsername();
        return "欢迎登录："+username;
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess() {
        String username = getUsername();
        return "欢迎登录："+username;
    }

    @PostMapping("/loginError")
    public String loginError() {
        return "用户名或密码错误";
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
}
