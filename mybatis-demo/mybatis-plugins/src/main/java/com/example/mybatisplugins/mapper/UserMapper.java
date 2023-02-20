package com.example.mybatisplugins.mapper;

import com.example.mybatisplugins.model.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/6 07:57
 */
public interface UserMapper {

    List<User> listUser();
    List<User> listUser(RowBounds rowBounds);
}
