package com.example.mybatis.model;

import lombok.Data;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/11 13:59
 */
@Data
public class Dept {
    private Integer id;
    private String deptName;

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
