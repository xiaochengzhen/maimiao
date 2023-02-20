package com.xiao.springdatajpadepend;

import com.xiao.springdatajpadepend.config.SpringDataJpaConfig;
import com.xiao.springdatajpadepend.model.manytomany.Role;
import com.xiao.springdatajpadepend.model.manytomany.User;
import com.xiao.springdatajpadepend.model.manytoone.MessageManyToOne;
import com.xiao.springdatajpadepend.model.manytoone.PersonManyToOne;
import com.xiao.springdatajpadepend.service.manytomany.UserRepository;
import com.xiao.springdatajpadepend.service.manytoone.MessageRepository;
import com.xiao.springdatajpadepend.service.manytoone.PersonManyToOneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 15:01
 */
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestManyToMany {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void test1() {
        User user = new User();
        user.setName("aaa");
        List<Role> list = new ArrayList<>();
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setRoleName("role1");
        role2.setRoleName("role2");
        list.add(role1);
        list.add(role2);
        user.setList(list);
        userRepository.save(user);
    }

    @Test
    @Transactional
    public void test2() {
        Optional<User> byId = userRepository.findById(1);
        System.out.println(byId.get());
    }


}