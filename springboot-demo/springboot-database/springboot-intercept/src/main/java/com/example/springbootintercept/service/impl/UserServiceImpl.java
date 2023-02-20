package com.example.springbootintercept.service.impl;

import com.example.springbootintercept.config.DynamicDataSource;
import com.example.springbootintercept.mapper.UserMapper;
import com.example.springbootintercept.model.User;
import com.example.springbootintercept.service.UserService;
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
    public User getUser(Integer id) {
      //  DynamicDataSource.name.set("datasource1");
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }
}
