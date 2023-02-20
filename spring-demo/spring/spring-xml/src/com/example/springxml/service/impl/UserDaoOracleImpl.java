package com.example.springxml.service.impl;

import com.example.springxml.service.UserDao;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/10 10:39
 */
public class UserDaoOracleImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("oracle 查询用户信息");
    }
}
