package com.xiao.springdatajpadepend.model.manytoone;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 15:42
 */
@Data
@Table(name = "tbl_message_many_to_one")
@Entity
public class MessageManyToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String info;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="person_id")
    private PersonManyToOne personManyToOne;


    public MessageManyToOne() {
    }

    public MessageManyToOne(String info, PersonManyToOne personManyToOne) {
        this.info = info;
        this.personManyToOne = personManyToOne;
    }
}
