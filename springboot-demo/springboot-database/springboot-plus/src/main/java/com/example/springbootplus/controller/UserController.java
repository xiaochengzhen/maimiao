package com.example.springbootplus.controller;

import com.example.springbootplus.model.User;
import com.example.springbootplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/{id}")
    public User getUser(Integer id) {
        return userServiceImpl.getUser(id);
    }

    @PutMapping
    public Integer saveUser(User user) {
        return userServiceImpl.saveUser(user);
    }
}
