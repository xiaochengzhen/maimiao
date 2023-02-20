package com.example.springbootplus.service.impl;

import com.example.springbootplus.config.DynamicDataSource;
import com.example.springbootplus.mapper.UserMapper;
import com.example.springbootplus.model.User;
import com.example.springbootplus.service.UserService;
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
        DynamicDataSource.name.set("datasource2");
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }
}
