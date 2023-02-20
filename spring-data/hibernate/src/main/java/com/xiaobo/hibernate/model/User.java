package com.xiaobo.hibernate.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 10:27
 */
@Entity
@Table(name = "tbl_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="user_name")
    private String userName;
    @Column(name="age")
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
