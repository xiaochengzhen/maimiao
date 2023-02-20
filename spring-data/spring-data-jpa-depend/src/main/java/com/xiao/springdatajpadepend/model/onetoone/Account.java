package com.xiao.springdatajpadepend.model.onetoone;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 14:52
 */
@Entity
@Data
@Table(name="tbl_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
}
