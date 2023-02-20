package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplusdemo.mapper.UserDao;
import com.example.mybatisplusdemo.model.User;
import com.example.mybatisplusdemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/7 17:34
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User listUser() {
        User user = userDao.selectById(1);
        return user;
    }

    @Override
    public List<User> listUserByParam() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName, "123");
        System.out.println("============");
        return userDao.selectList(lambdaQueryWrapper);
    }

    @Override
    public void save1(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.updateById(user);
    }
    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }


}
