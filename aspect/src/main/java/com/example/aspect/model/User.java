package com.example.aspect.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 邮箱
     */
    private String email;

    private Date updateTime;

    private Date createTime;

    private Integer version;

    private Integer companyId;

    private static final long serialVersionUID = 1L;

    public User(Long id) {
        this.id = id;
    }
}