package com.xiao.springdatajpadepend.model.manytomany;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/29 08:52
 */
@Data
@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="role_name")
    private String roleName;
}
