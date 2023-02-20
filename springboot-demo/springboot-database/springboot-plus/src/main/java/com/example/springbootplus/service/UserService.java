package com.example.springbootplus.service;

import com.example.springbootplus.model.User;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:15
 */
public interface UserService {

    User getUser(Integer id);

    Integer saveUser(User user);
}
