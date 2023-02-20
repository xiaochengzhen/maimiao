package com.example.springxml.service.impl;

import com.example.springxml.service.UserDao;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/10 10:18
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("mysql 获取用户信息");
    }
}
