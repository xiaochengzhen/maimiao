package com.example.craw.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 财务指标的实体类
 * tb_company_financial_indicator
 * @author 
 */
@Data
public class CompanyFinancialIndicatorDO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标的代码
     */
    private String symbol;

    /**
     * 周期：1=季度； 2=累计季度； 3=年度
     */
    private Integer period;

    /**
     * 每股收益信息
     */
    private String eps;

    /**
     * 每股净资产
     */
    private String bvps;

    /**
     * 流动比率信息信息
     */
    private String currentRatio;

    /**
     * 速动比率
     */
    private String quickRatio;

    /**
     * 净资产收益率
     */
    private String roe;

    /**
     * 总资产收益率
     */
    private String roa;

    /**
     * 毛利率
     */
    private String grossMargin;

    /**
     * 净利率
     */
    private String netMargin;

    /**
     * 自由现金流
     */
    private String fcf;

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