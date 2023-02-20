package com.xiao.springdatajpadepend.model.onetomany;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 15:41
 */
@Entity
@Data
@Table(name = "tbl_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="person_id")
    private List<Message> list;
}
