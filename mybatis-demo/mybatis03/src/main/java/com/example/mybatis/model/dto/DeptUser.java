package com.example.mybatis.model.dto;

import com.example.mybatis.model.Dept;
import com.example.mybatis.model.User;
import lombok.Data;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/11 14:05
 */
@Data
public class DeptUser extends Dept {
    private List<User> list;

    @Override
    public String toString() {
        return super.toString()+"DeptUser{" +
                "list=" + list +
                '}';
    }
}
