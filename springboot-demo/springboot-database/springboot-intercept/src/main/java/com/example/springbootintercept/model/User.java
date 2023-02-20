package com.example.springbootintercept.model;

import lombok.Data;

import java.io.Serializable;

/**
 * tbl_user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private static final long serialVersionUID = 1L;
}