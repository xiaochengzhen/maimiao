package com.example.craw;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * tb_company_executives
 * @author 
 */
@Data
public class CompanyExecutivesDO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标的代码
     */
    private String symbol;

    /**
     * 名称
     */
    private String name;

    /**
     * 职位
     */
    private String position;

    /**
     * 薪资
     */
    private BigDecimal salary;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 1:男 2:女
     */
    private Integer gender;

    /**
     * 个人介绍
     */
    private String description;

    /**
     * 顺序
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}