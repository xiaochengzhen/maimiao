package com.xiao.springdatajpadepend.model.onetomany;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 15:42
 */
@Data
@Table(name = "tbl_message")
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String info;

    public Message() {
    }

    public Message(String info) {
        this.info = info;
    }
}
