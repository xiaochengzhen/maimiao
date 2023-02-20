package com.example.springaop.service.impl;

import com.example.springaop.model.User;
import com.example.springaop.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author xiaobo
 * @description1
 * @date 2022/8/25 16:34
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void start() {
        System.out.println("======开始======");
    }

    @Override
    public User play() {
        System.out.println("======玩游戏=====");
        User user = new User();
        user.setName("xiaobo");
        return user;
    }
}
