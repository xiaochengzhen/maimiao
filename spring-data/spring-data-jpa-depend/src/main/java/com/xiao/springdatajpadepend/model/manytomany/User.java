package com.xiao.springdatajpadepend.model.manytomany;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/29 08:50
 */
@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="tbl_user_role",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")})
    private List<Role> list;
}
