package com.xiao.springdatajpa;

import com.xiao.springdatajpa.config.SpringDataJpaConfig;
import com.xiao.springdatajpa.model.User;
import com.xiao.springdatajpa.service.UserPageRepository;
import com.xiao.springdatajpa.service.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:06
 */
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPageRepository userPageRepository;

    @org.junit.Test
    public void test1() {
        Iterable<User> all = userRepository.findAll();
        for (User user : all) {
            System.out.println(user);
            System.out.println("te4st");
        }
    }

    @org.junit.Test
    public void test2() {
        User user = new User();
        user.setUserName("12");
        user.setAge(10);
        user.setId(100);
        User save = userRepository.save(user);   //  save 方法不同于之前的理解，如果传入id，他会先查询，如果存在修改，不存在插入, 插入的时候不会用传入的id
        save.setUserName("bbb");       //不会修改， 因为事务已经提交
        user.setUserName("bbb");       //不会修改，因为事务已经提交
        System.out.println(save);
    }

    @org.junit.Test
    public void test3() {
        User user = new User();
        user.setId(7);
        userRepository.delete(user); // delete 他会想查询，如果有就delete， 如果没有，啥都不做
    }

    @org.junit.Test
    public void testPage() {
        Page<User> all = userPageRepository.findAll(PageRequest.of(0, 2));
        long totalElements = all.getTotalElements();
        Iterator<User> iterator = all.iterator();
        System.out.println(totalElements);
        while (iterator.hasNext()) {
            User next = iterator.next();
            System.out.println(next);
        }
    }
}
