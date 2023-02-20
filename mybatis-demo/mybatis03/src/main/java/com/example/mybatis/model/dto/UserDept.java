package com.example.mybatis.model.dto;

import com.example.mybatis.model.Dept;
import com.example.mybatis.model.User;
import lombok.Data;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/11 14:05
 */
@Data
public class UserDept extends User {
    private Dept dept;

}
