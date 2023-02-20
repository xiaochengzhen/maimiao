package com.example.mybatis.mapper;

import com.example.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/6 07:57
 */
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User selectUser(@Param("id") Integer id);

    void save(User user);
}
