package com.example.springbootplus.model;

import java.io.Serializable;
import lombok.Data;

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