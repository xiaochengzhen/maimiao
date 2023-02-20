package com.example.springbootaop.service.impl;

import com.example.springbootaop.annotion.AopAnnotation;
import com.example.springbootaop.mapper.UserMapper;
import com.example.springbootaop.model.User;
import com.example.springbootaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:16
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    @AopAnnotation("datasource1")
    public User getUser(Integer id) {
      //  DynamicDataSource.name.set("datasource1");
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }
}
