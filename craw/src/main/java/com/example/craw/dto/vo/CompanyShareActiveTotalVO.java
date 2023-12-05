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
public class CompanyShareActiveTotalVO implements Serializable {


    /**
     * 机构数量
     */
    private Integer count;

    /**
     * 变动后持股数量
     */
    private BigDecimal shareValue;

    /**
     * 变动后持股比例
     */
    private BigDecimal shareRatio;

    private static final long serialVersionUID = 1L;
}