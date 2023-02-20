package com.xiao.springdatajpa;

import com.xiao.springdatajpa.config.SpringDataJpaConfig;
import com.xiao.springdatajpa.model.User;
import com.xiao.springdatajpa.service.UserQueryByExampleRepository;
import com.xiao.springdatajpa.service.UserSelfRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:06
 */
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestQueryByExample {
    @Autowired
    private UserQueryByExampleRepository userQueryByExampleRepository;

    @org.junit.Test
    public void test1() {
        User user = new User();
        user.setUserName("a");
        Example example = Example.of(user);
        List<User> list = (List<User>) userQueryByExampleRepository.findAll(example);
        System.out.println(list);
    }

    @org.junit.Test
    public void test2() {
        User user = new User();
        user.setUserName("a");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
            .withIgnorePaths("userName");
        Example example = Example.of(user, exampleMatcher);
        List<User> list = (List<User>) userQueryByExampleRepository.findAll(example);
        System.out.println(list);
    }



}
