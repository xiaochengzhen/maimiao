package com.example.craw.dto.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 财务指标的实体类
 * tb_company_financial_indicator
 * @author 
 */
@Data
public class CompanyFinancialIndicatorVO implements Serializable {

    /**
     * 每股收益信息
     */
    private List<ListSingleIndicatorVO> eps;

    /**
     * 每股净资产
     */
    private List<ListSingleIndicatorVO> bvps;

    /**
     * 流动比率信息信息
     */
    private List<ListSingleIndicatorVO> currentRatio;

    /**
     * 速动比率
     */
    private List<ListSingleIndicatorVO> quickRatio;

    /**
     * 净资产收益率
     */
    private List<ListSingleIndicatorVO> roe;

    /**
     * 总资产收益率
     */
    private List<ListSingleIndicatorVO> roa;

    /**
     * 毛利率
     */
    private List<ListSingleIndicatorVO> grossMargin;

    /**
     * 净利率
     */
    private List<ListSingleIndicatorVO> netMargin;

    /**
     * 自由现金流
     */
    private List<ListSingleIndicatorVO> fcf;


    private static final long serialVersionUID = 1L;
}