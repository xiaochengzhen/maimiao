package com.example.craw.http;
/**
 * @description 
 * @author xiaobo
 * @date 2023/11/30 14:44
 */

public enum CrawEnum {

    MAIN_COMPOSITION("MAIN_COMPOSITION","股票主页面", "", "","en_US", false),
    FINANCIAL_REAL_EN("FINANCIAL_REAL","营收", "", "", "en_US", false),
    FINANCIAL_REAL_ZH("FINANCIAL_REAL","营收", "", "", "zh_CN", false),
    COMPANY_MAIN_REGION_HK_EN("COMPANY_MAIN_HK","港股公司主营地区英文", "4", "", "en_US", false),
    COMPANY_MAIN_REGION_HK_ZH("COMPANY_MAIN_HK","港股公司主营地区中文", "4", "", "zh_CN", false),
    COMPANY_MAIN_BUSINESS_HK_EN("COMPANY_MAIN_HK","港股公司主营业务英文", "8", "", "en_US", false),
    COMPANY_MAIN_BUSINESS_HK_ZH("COMPANY_MAIN_HK","港股公司主营业务中文", "8", "", "zh_CN", false),
    COMPANY_MAIN_REGION_BEFORE_US_EN("COMPANY_MAIN_BEFORE_US","美股公司主营地区英文", "4", "", "en_US", false),
    COMPANY_MAIN_REGION_US_EN("COMPANY_MAIN_US","美股公司主营地区英文", "4", "", "en_US", true),
    COMPANY_MAIN_REGION_US_ZH("COMPANY_MAIN_US","美股公司主营地区中文", "4", "", "zh_CN", true),
    COMPANY_MAIN_BUSINESS_BEFORE_US_EN("COMPANY_MAIN_BEFORE_US","美股公司主营业务英文", "8", "", "en_US", false),
    COMPANY_MAIN_BUSINESS_US_EN("COMPANY_MAIN_US","美股公司主营业务英文", "8", "", "en_US", true),
    COMPANY_MAIN_BUSINESS_US_ZH("COMPANY_MAIN_US","美股公司主营业务中文", "8", "", "zh_CN", true),
    COMPANY_FINANCIAL_INDICATOR_Q_ZH("COMPANY_FINANCIAL_INDICATOR","财务指标单季", "12", "3,4,21,22,24,25,80,81,82", "zh_CN", false),
    COMPANY_FINANCIAL_INDICATOR_H_ZH("COMPANY_FINANCIAL_INDICATOR","财务指标累季", "13", "3,4,21,22,24,25,80,81,82", "zh_CN", false),
    COMPANY_FINANCIAL_INDICATOR_Y_ZH("COMPANY_FINANCIAL_INDICATOR","财务指标年", "11", "3,4,21,22,24,25,80,81,82", "zh_CN", false),
    COMPANY_INCOME_ZH("COMPANY_INCOME","利润表", "", "", "zh_CN", false);

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
