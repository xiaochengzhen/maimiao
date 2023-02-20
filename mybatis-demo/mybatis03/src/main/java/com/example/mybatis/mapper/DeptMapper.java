package com.example.mybatis.mapper;

import com.example.mybatis.model.User;
import com.example.mybatis.model.dto.DeptUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/6 07:57
 */
public interface DeptMapper {

    List<DeptUser> listDetpUser();

    List<DeptUser> listDetpUser2();
}
