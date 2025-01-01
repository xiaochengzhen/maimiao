package com.example.reptile.webmagic.news.enums;

public enum NewsEnum {

    HEADLINES_ZH(1,"", "https://cn.investing.com/news/headlines", "zh_CN", "要闻中文"),
    HEADLINES_EN(1,"", "https://www.investing.com/news/headlines", "en_US", "要闻英文"),
    HK_STOCK_NEWS_ZH(2,"港股市场", "https://cn.investing.com/search/?q=%E6%B8%AF%E8%82%A1%E5%B8%82%E5%9C%BA&tab=news", "zh_CN", "港股新闻中文"),
    HK_STOCK_NEWS_EN(2,"HK stock", "https://www.investing.com/search/?q=HK%20stock&tab=news", "en_US", "港股新闻英文"),
    US_STOCK_NEWS_ZH(2,"美股市场", "https://cn.investing.com/search/?q=%E7%BE%8E%E8%82%A1%E5%B8%82%E5%9C%BA&tab=news", "zh_CN", "美股新闻中文"),
    US_STOCK_NEWS_EN(2,"US stock", "https://www.investing.com/search/?q=US%20stock&tab=news", "en_US", "美股新闻英文"),
    SYMBOL_ZH(3,"", "https://cn.investing.com/search/?q={}&tab=news", "zh_CN", "个股资讯中文"),
    SYMBOL_EN(3,"", "https://www.investing.com/search/?q={}&tab=news", "en_US", "个股资讯英文");

    private int htmlType;
    private String keyword;
    private String url;
    private String language;
    private String desc;

    NewsEnum(int htmlType, String keyword, String url, String language, String desc) {
        this.htmlType = htmlType;
        this.keyword = keyword;
        this.url = url;
        this.language = language;
        this.desc = desc;
    }
}
