package com.example.springbootintercept.service;

import com.example.springbootintercept.model.User;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:15
 */
public interface UserService {

    User getUser(Integer id);

    Integer saveUser(User user);
}
