package com.example.craw.dto.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description 查询财务指标的响应类
 * @author xiaobo
 * @date 2023/12/4 13:52
 */
@Data
public class ListSingleIncomeStatementVO {

    /**
     * 金额
     */
    private BigDecimal value;

    /**
     * 增长率
     */
    private String ratio;

    /**
     * 周期
     */
    private String quarter;

}
