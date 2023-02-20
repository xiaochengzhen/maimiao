package com.xiao.springdatajpa;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.xiao.springdatajpa.config.SpringDataJpaConfig;
import com.xiao.springdatajpa.model.QUser;
import com.xiao.springdatajpa.model.User;
import com.xiao.springdatajpa.service.UserQueryByExampleRepository;
import com.xiao.springdatajpa.service.UserQueryDSLRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Persistence;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:06
 */
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestQueryDSL {
    @Autowired
    private UserQueryDSLRepository userQueryDSLRepository;

    @org.junit.Test
    public void test1() {
        QUser qUser = QUser.user;
        BooleanExpression eq = qUser.id.eq(4);
        List<User> list = (List<User>) userQueryDSLRepository.findAll(eq);
        System.out.println(list);

    }




}
