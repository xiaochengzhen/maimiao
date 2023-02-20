package com.xiao.springdatajpa;

import com.xiao.springdatajpa.config.SpringDataJpaConfig;
import com.xiao.springdatajpa.model.User;
import com.xiao.springdatajpa.service.UserPageRepository;
import com.xiao.springdatajpa.service.UserRepository;
import com.xiao.springdatajpa.service.UserSelfRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:06
 */
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSelf {
    @Autowired
    private UserSelfRepository userSelfRepository;

    @org.junit.Test
    public void test1() {
        List<User> users = userSelfRepository.listUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void test2() {
        User userById = userSelfRepository.getUserById(3);
        System.out.println(userById);
    }

    @org.junit.Test
    public void test3() {
        User userById = userSelfRepository.getUserByIdNative(3);
        System.out.println(userById);
    }

    @org.junit.Test
    public void test4() {
        userSelfRepository.updateUser(3, 5);
    }



}
