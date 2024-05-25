package com.example.ebangbase.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/26 11:19
 */
@Data
public class TestDO {
    private Integer id;

    private Timestamp createTime;
    private Timestamp updateTime;
    private String name;
    private Long age;
}
