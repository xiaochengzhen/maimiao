package com.example.craw.dto.query;

import lombok.Data;

/**
 * @description 查询财务指标的请求类
 * @author xiaobo
 * @date 2023/12/4 13:52
 */
@Data
public class ListCompanyFinancialIndicatorQuery {

    /**
     * 标的
     */
    private String symbol;

    /**
     * 周期：1=季度； 2=累计季度； 3=年度
     */
    private Integer period;

    /**
     * 指标类型：1=每股收益； 2=每股净资产； 3=流动比率； 4=速动比率； 5=净资产收益率； 6=总资产收益率； 7=毛利率；8=净利率； 9=自由现金流
     */
    private Integer indicatorType;
}
