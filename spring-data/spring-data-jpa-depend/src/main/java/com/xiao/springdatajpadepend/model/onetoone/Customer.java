package com.xiao.springdatajpadepend.model.onetoone;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 14:52
 */
@Data
@Entity
@Table(name = "tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "account_id")
    private Account account;
}
