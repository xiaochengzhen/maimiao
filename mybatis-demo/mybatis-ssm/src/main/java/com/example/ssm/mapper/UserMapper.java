package com.example.ssm.mapper;

import com.example.ssm.model.User;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/6 07:57
 */
public interface UserMapper {

    List<User> selectUser();
}
