package com.example.springannotionxml.service.impl;

import com.example.springannotionxml.model.User;
import com.example.springannotionxml.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/23 09:33
 */
@Service
@Primary
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return null;
    }
}
