package com.example.ssm.controller;

import com.example.ssm.model.User;
import com.example.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/15 15:01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/list")
    public List<User> getList() {
        return userServiceImpl.listUser();
    }
    @RequestMapping("/list1")
    public String getList1() {
        return "helloworld";
    }

    @RequestMapping("/list2")
    public String getList2() {
        return userServiceImpl.listUser().get(0).toString();
    }
}
