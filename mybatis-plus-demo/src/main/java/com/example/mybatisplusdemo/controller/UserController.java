package com.example.mybatisplusdemo.controller;

import com.example.mybatisplusdemo.mapper.TableUserMapper;
import com.example.mybatisplusdemo.model.TableUser;
import com.example.mybatisplusdemo.model.User;
import com.example.mybatisplusdemo.service.UserService;
import com.github.fashionbrot.validated.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/7 17:37
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TableUserMapper tableUserMapper;

    @GetMapping
    public User getUser() {
        return userService.listUser();
    }


    @Validated
    @PostMapping
    public void saveTableUser(@RequestBody TableUser tableUser) {
        tableUserMapper.insert(tableUser);
    }

    @Validated
    @PutMapping
    public void updateById(@RequestBody TableUser tableUser) {
        tableUserMapper.updateById(tableUser);
    }
}
