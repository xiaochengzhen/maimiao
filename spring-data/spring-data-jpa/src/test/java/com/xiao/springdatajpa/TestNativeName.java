package com.xiao.springdatajpa;

import com.xiao.springdatajpa.config.SpringDataJpaConfig;
import com.xiao.springdatajpa.model.User;
import com.xiao.springdatajpa.service.UserNativeNameRepository;
import com.xiao.springdatajpa.service.UserSelfRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestNativeName {
    @Autowired
    private UserNativeNameRepository userNativeNameRepository;

    @org.junit.Test
    public void test1() {
        User byAgeAndId = userNativeNameRepository.findByAgeAndId(5, 3);
        System.out.println(byAgeAndId);
    }

    @org.junit.Test
    public void test2() {
       userNativeNameRepository.deleteByIdAndAge(3, 5);
    }





}
