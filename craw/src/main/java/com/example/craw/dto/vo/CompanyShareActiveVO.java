package com.example.craw.dto.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tb_company_share_active
 * @author 
 */
@Data
public class CompanyShareActiveVO implements Serializable {

    /**
     * 日期
     */
    private String date;

    /**
     * 业务订单 ID
     */
    private String name;

    /**
     * 变动股数
     */
    private BigDecimal quantity;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 变动后持股数量
     */
    private BigDecimal shareValue;

    /**
     * 变动后持股比例
     */
    private BigDecimal shareRatio;

}