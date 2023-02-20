package com.example.springbootaop.service;


import com.example.springbootaop.model.User;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:15
 */
public interface UserService {

    User getUser(Integer id);

    Integer saveUser(User user);
}
