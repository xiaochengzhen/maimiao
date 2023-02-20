package com.example.springaop.service.impl;

import com.example.springaop.model.User;
import com.example.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/25 16:37
 */
//@Service
public class ProxyUser implements UserService {

    private UserService userServiceImpl;

    public ProxyUser(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void start() {
        userServiceImpl.start();
    }

    @Override
    public User play() {
        System.out.println("=============前=============");
        userServiceImpl.play();
        System.out.println("=============后=============");
        return new User();
    }

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserService userService1 = new ProxyUser(userService);
        userService1.play();
    }
}
