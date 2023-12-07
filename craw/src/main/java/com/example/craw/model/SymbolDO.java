package com.example.craw.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * tb_symbol
 * @author 
 */
@Data
public class SymbolDO implements Serializable {
    private Long id;

    /**
     * symbol(AAPL.us)
     */
    private String symbol;

    /**
     * 官方标的代码（前端使用）
     */
    private String officeSymbol;

    /**
     * 结算币种
     */
    private String currency;

    /**
     * 底层标的代码
     */
    private String baseSymbol;

    /**
     * 股票简称
     */
    private String shortName;

    /**
     * 股票全称
     */
    private String fullName;

    /**
     * 中文名称
     */
    private String cnName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 产品类型（1-股票；2-期权；3-货币兑换）
     */
    private Byte productType;

    /**
     * 最小交易单位
     */
    private BigDecimal lotSize;

    /**
     * 比如纳斯达克股(港股：MAIN（主板）、GEM（创业板）、NASD（NASDAQ AMX市场）、ETS（扩充交易证券）； 美股：Q (Nasdaq全球精选市场)/ G (Nasdaq全球市场)/ S (Nasdaq资本市场)/ N (NYSE纽约交易所)/ A (NYSE MKT（AMEX) / P (NYSE Arca) / Z (BATS交易所) / V (Investor's Exchange，LLC))
     */
    private String region;

    /**
     * 市场 hk:港股 us:美股 fx:货币兑换
     */
    private String market;

    /**
     * 市场状态（0-休市；1-正常；2-闭市）
     */
    private Byte marketStatus;

    /**
     * 股票状态
     */
    private Byte securityStatus;

    /**
     * 显示状态（0-隐藏；1-显示）
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 是否支持碎股（0-不支持；1-支持）
     */
    private Boolean isShortStoke;

    /**
     * FRR 等级
     */
    private String frrGrade;

    /**
     * 上市日期
     */
    private String listingDay;

    /**
     * 是否是热门股票 1：热门股 0：非热门股
     */
    private Byte isHot;

    /**
     * 允许多个关键词，用“，”号区隔
     */
    private String keyWord;

    /**
     * 发布状态 0：待发布 1：预发布 2：已发布
     */
    private Boolean releaseStatus;

    /**
     * 交易状态 0：不允许交易 1：暗盘交易 2：正常交易
     */
    private Boolean tradeStatus;

    private static final long serialVersionUID = 1L;
}