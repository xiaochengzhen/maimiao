package com.example.springbootintercept;

import com.example.springbootintercept.model.User;
import com.example.springbootintercept.service.UserService;
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
