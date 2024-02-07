package com.example.craw.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_symbol_grade
 * @author 
 */
@Data
public class SymbolGradeDO implements Serializable {

    private Long id;

    /**
     * 标的
     */
    private String symbol;

    /**
     * 市场 hk:港股 us:美股 fx:货币兑换
     */
    private String market;

    /**
     * 类型：1=恒生指数； 2=恒生综合大型股指数； 3=标普500
     */
    private Integer type;

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