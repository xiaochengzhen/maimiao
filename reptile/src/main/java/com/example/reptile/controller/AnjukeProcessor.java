package com.example.reptile.controller;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @description
 * @author xiaobo
 * @date 2024/2/28 13:34
 */
public class AnjukeProcessor implements PageProcessor {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent("");

    @Override
    public void process(Page page) {
        int statusCode = page.getStatusCode();
        Html html = page.getHtml();
        String s = html.get();
        System.out.println(s);
        System.out.println(statusCode);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new AnjukeProcessor())
                .addUrl("https://www.futunn.com/stock/00017-HK/company")
                .run();
    }
}
