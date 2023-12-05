package com.example.craw.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 财务指标汇总的实体类
 * tb_company_financial_real
 * @author 
 */
@Data
public class CompanyFinancialRealDO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 券商id
     */
    private String symbol;

    /**
     * 每股收益
     */
    private BigDecimal earningsPerShare;

    /**
     * 总收入
     */
    private BigDecimal revenue;

    /**
     * 持续经营活动现金
     */
    private BigDecimal cashFromOperating;

    /**
     * 净资产收益率
     */
    private BigDecimal returnOnEquity;

    /**
     * 毛利率
     */
    private BigDecimal grossMargin;

    /**
     * 流动比率
     */
    private BigDecimal currentRatio;

    /**
     * 根据最新一季度的主营构成数据
     */
    private String compositionData;

    /**
     * 基础币种
     */
    private String currency;

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