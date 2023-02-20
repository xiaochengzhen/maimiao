package com.xiao.springdatajpadepend.model.manytoone;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 15:41
 */
@Entity
@Data
@Table(name = "tbl_person_many_to_one")
public class PersonManyToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

}
