package com.example.craw.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 主营业务构成的实体类
 * tb_company_main_composition
 * @author 
 */
@Data
public class CompanyMainCompositionDO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标的代码
     */
    private String symbol;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 业务营业数据
     */
    private String business;

    /**
     * 地区营业数据
     */
    private String region;

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