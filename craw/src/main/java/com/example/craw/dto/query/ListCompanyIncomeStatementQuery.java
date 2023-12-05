package com.example.craw.dto.query;

import lombok.Data;

/**
 * @description 查询利润表的请求类
 * @author xiaobo
 * @date 2023/12/4 13:52
 */
@Data
public class ListCompanyIncomeStatementQuery {

    /**
     * 标的
     */
    private String symbol;

    /**
     * 周期：1=季度； 2=累计季度； 3=年度
     */
    private Integer period;

    /**
     * 指标类型：1=营业收入; 2=营业利润; 3=净利润；
     */
    private Integer incomeType;
}
