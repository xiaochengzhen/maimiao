package com.example.craw.http;
/**
 * @description 
 * @author xiaobo
 * @date 2023/11/30 14:44
 */

public enum CrawEnum {

    MAIN_COMPOSITION("MAIN_COMPOSITION","股票主页面", "0", "en"),
    FINANCIAL_REAL_EN("FINANCIAL_REAL","营收", "0", "en"),
    FINANCIAL_REAL_ZH("FINANCIAL_REAL","营收", "0", "zh"),
    COMPANY_MAIN_REGION_EN("COMPANY_MAIN","公司主营地区英文", "4", "en"),
    COMPANY_MAIN_REGION_ZH("COMPANY_MAIN","公司主营地区中文", "4", "zh"),
    COMPANY_MAIN_BUSINESS_EN("COMPANY_MAIN","公司主营业务英文", "8", "en"),
    COMPANY_MAIN_BUSINESS_ZH("COMPANY_MAIN","公司主营业务中文", "8", "zh");

    /**
     * 描述
     */
    private String code;
    /**
     * 描述
     */
    private String desc;
    /**
     * 类型
     */
    private String type;
    /**
     * 语言
     */
    private String language;

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    CrawEnum(String code, String desc, String type, String language) {
        this.code = code;
        this.desc = desc;
        this.type = type;
        this.language = language;
    }

}
