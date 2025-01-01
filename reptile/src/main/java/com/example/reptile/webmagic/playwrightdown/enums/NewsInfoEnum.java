package com.example.reptile.webmagic.playwrightdown.enums;

import lombok.Getter;

@Getter
public enum NewsInfoEnum {

    HEADLINES_ZH(1,"", "https://cn.investing.com/news/headlines", "zh_CN", "要闻中文"),
    HEADLINES_EN(2,"", "https://www.investing.com/news/headlines", "en_US", "要闻英文"),
    HK_STOCK_NEWS_ZH(3,"港股市场", "https://cn.investing.com/search/?q=%E6%B8%AF%E8%82%A1%E5%B8%82%E5%9C%BA", "zh_CN", "港股新闻中文"),
    HK_STOCK_NEWS_EN(4,"HK stock", "https://www.investing.com/search/?q=HK%20stock", "en_US", "港股新闻英文"),
    US_STOCK_NEWS_ZH(5,"美股市场", "https://cn.investing.com/search/?q=%E7%BE%8E%E8%82%A1%E5%B8%82%E5%9C%BA", "zh_CN", "美股新闻中文"),
    US_STOCK_NEWS_EN(6,"US stock", "https://www.investing.com/search/?q=US%20stock", "en_US", "美股新闻英文"),
    SYMBOL_ZH(7,"", "https://cn.investing.com/search", "zh_CN", "个股资讯中文"),
    SYMBOL_EN(8,"", "https://www.investing.com/search", "en_US", "个股资讯英文");

    private int htmlType;
    private String keyword;
    private String url;
    private String language;
    private String desc;

    NewsInfoEnum(int htmlType, String keyword, String url, String language, String desc) {
        this.htmlType = htmlType;
        this.keyword = keyword;
        this.url = url;
        this.language = language;
        this.desc = desc;
    }


}
