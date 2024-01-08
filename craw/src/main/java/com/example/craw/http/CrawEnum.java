package com.example.craw.http;
/**
 * @description 请求接口类型枚举
 * @author xiaobo
 * @date 2023/11/30 14:44
 */

public enum CrawEnum {

    MAIN_COMPOSITION("MAIN_COMPOSITION","股票主页面", "", "","zh_CN", false),
    FINANCIAL_REAL_ZH("FINANCIAL_REAL","营收", "", "", "zh_CN", false),
    FINANCIAL_REAL_EN("FINANCIAL_REAL","营收", "", "", "en_US", false),
    COMPANY_MAIN_REGION_BEFORE("COMPANY_MAIN_BEFORE","公司主营地区查询日期", "4", "", "zh_CN", false),
    COMPANY_MAIN_BUSINESS_BEFORE("COMPANY_MAIN_BEFORE","公司主营业务查询日期", "8", "", "zh_CN", false),
    COMPANY_MAIN_ZH("COMPANY_MAIN","公司主营中文", "4", "", "zh_CN", true),
    COMPANY_MAIN_EN("COMPANY_MAIN","公司主营英文", "4", "", "en_US", true),
    COMPANY_FINANCIAL_INDICATOR_Q_ZH("COMPANY_FINANCIAL_INDICATOR","财务指标单季", "12", "3,4,21,22,24,25,80,81,82", "zh_CN", false),
    COMPANY_FINANCIAL_INDICATOR_H_ZH("COMPANY_FINANCIAL_INDICATOR","财务指标累季", "13", "3,4,21,22,24,25,80,81,82", "zh_CN", false),
    COMPANY_FINANCIAL_INDICATOR_Y_ZH("COMPANY_FINANCIAL_INDICATOR","财务指标年", "11", "3,4,21,22,24,25,80,81,82", "zh_CN", false),
    COMPANY_INCOME_HK_1_ZH("COMPANY_INCOME_HK","港股利润表(1季度)", "1", "", "zh_CN", false),
    COMPANY_INCOME_HK_2_ZH("COMPANY_INCOME_HK","港股利润表(中报)", "2", "", "zh_CN", false),
    COMPANY_INCOME_HK_3_ZH("COMPANY_INCOME_HK","港股利润表(3季度)", "3", "", "zh_CN", false),
    COMPANY_INCOME_HK_4_ZH("COMPANY_INCOME_HK","港股利润表(年报)", "4", "", "zh_CN", false),
    COMPANY_INCOME_US_5_ZH("COMPANY_INCOME_US","美股利润表(年)", "5", "", "zh_CN", false),
    COMPANY_INCOME_US_6_ZH("COMPANY_INCOME_US","美股利润表(季)", "6", "", "zh_CN", false),
    COMPANY_INCOME_US_7_ZH("COMPANY_INCOME_US","美股利润表(累计季)", "7", "", "zh_CN", false);

    /**
     * 描述
     */
    private String code;
    /**
     * 描述
     */
    private String desc;
    /**
     * 类型 4=公司主营地区； 8=公司主营业务 ； 11=财务指标年； 12=财务指标单季； 13=财务指标累季
     */
    private String type;
    /**
     * 指标
     */
    private String levelThreeType;
    /**
     * 语言
     */
    private String language;
    /**
     * 是否跳过
     */
    private Boolean skip;

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getLevelThreeType() {
        return levelThreeType;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getSkip() {
        return skip;
    }

    CrawEnum(String code, String desc, String type, String levelThreeType, String language, Boolean skip) {
        this.code = code;
        this.desc = desc;
        this.type = type;
        this.levelThreeType = levelThreeType;
        this.language = language;
        this.skip = skip;
    }

}
