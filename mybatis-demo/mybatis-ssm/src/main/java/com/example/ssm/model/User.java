package com.example.ssm.model;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", deptId=" + deptId +
                '}';
    }
}
