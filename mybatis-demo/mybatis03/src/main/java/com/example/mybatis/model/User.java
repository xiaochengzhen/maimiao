package com.example.mybatis.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/6 07:59
 */
@Alias("user")
@Data
public class User {

    private Integer id;
    private String username;
    private Integer age;
    private Integer deptId;
}
