package com.example.craw.dto.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description 查询财务指标的响应类
 * @author xiaobo
 * @date 2023/12/4 13:52
 */
@Data
public class ListSingleIndicatorVO {

    /**
     * 年
     */
    private String year;

    /**
     * 周期类型
     */
    private Integer financialType;

    /**
     * 营收
     */
    private BigDecimal indicatorData;

    /**
     * 增长率
     */
    private BigDecimal otherIndicator;

    /**
     * 周期
     */
    private String quarter;

}
