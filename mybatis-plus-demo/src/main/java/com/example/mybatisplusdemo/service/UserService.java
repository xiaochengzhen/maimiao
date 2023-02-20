package com.example.mybatisplusdemo.service;

import com.example.mybatisplusdemo.model.User;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/7 17:33
 */
public interface UserService extends BaseService<User> {

    User listUser();
    List<User> listUserByParam();
    void save1(User user);
    void update(User user);
    void delete(Long id);
}
