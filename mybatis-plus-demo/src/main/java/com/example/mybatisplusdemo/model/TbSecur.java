package com.example.mybatisplusdemo.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_secur
 * @author 
 */
@Data
public class TbSecur implements Serializable {
    private Integer id;

    private Byte age;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}