package com.example.reptile.webmagic.playwrightdown.service;

import com.example.reptile.webmagic.playwrightdown.enums.NewsInfoEnum;
import com.example.reptile.webmagic.playwrightdown.service.impl.NewsInfoCrawHeadlinesService;
import com.example.reptile.webmagic.playwrightdown.service.impl.NewsInfoCrawStockService;
import com.example.reptile.webmagic.playwrightdown.service.impl.NewsInfoCrawSymbolService;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Request;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/30 11:11
 */
@Service
public class NewsInfoCrawService {

    @Autowired
    private NewsInfoCrawHeadlinesService newsInfoCrawHeadlinesService;

    @Autowired
    private NewsInfoCrawSymbolService newsInfoCrawSymbolService;

    @Autowired
    private NewsInfoCrawStockService newsInfoCrawStockService;

    public void crawHeadlines() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            newsInfoCrawHeadlinesService.createSpider(browser, getReqests(NewsInfoEnum.HEADLINES_ZH, NewsInfoEnum.HEADLINES_EN));
            browser.close();
        }
    }

    public void crawStock() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            newsInfoCrawStockService.createSpider(browser, getReqests(NewsInfoEnum.HK_STOCK_NEWS_ZH, NewsInfoEnum.HK_STOCK_NEWS_EN
                    ,NewsInfoEnum.US_STOCK_NEWS_ZH, NewsInfoEnum.US_STOCK_NEWS_EN));
            browser.close();
        }

    }

    public void crawSymbol() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            newsInfoCrawSymbolService.createSpider(browser, getReqests(NewsInfoEnum.SYMBOL_ZH, NewsInfoEnum.SYMBOL_EN));
            browser.close();
        }
    }


    private Request[] getReqests(NewsInfoEnum ... enums) {
        Request [] requests = new Request[enums.length];
        for (int i = 0; i < enums.length; i++) {
            NewsInfoEnum newsInfoEnum = enums[i];
            Request request = new Request(newsInfoEnum.getUrl());
            request.putExtra("craw", newsInfoEnum);
            requests[i] = request;
        }
        return requests;
    }



}
