package com.example.springbootplus.test;

import com.example.springbootplus.config.DynamicDataSource;
import com.example.springbootplus.model.User;
import com.example.springbootplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:21
 */
@SpringBootTest
public class TestUser {

    @Autowired
    private UserService userServiceImpl;
    @Test
    public void getUser() {
        Integer id = 1;
        User user = userServiceImpl.getUser(id);
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setAge(12);
        user.setName("a");
        Integer id = userServiceImpl.saveUser(user);
        System.out.println(id);
    }
}
