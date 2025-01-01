package com.example.reptile.webmagic.playwrightdown.service.impl;

import com.example.reptile.webmagic.getrequest.annonation.mapper.SymbolMapper;
import com.example.reptile.webmagic.news.model.NewsInfoDTO;
import com.example.reptile.webmagic.playwrightdown.enums.NewsInfoEnum;
import com.example.reptile.webmagic.playwrightdown.service.PlayWrightDownloader;
import com.microsoft.playwright.Browser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/30 10:13
 */
@Slf4j
@Service
public class NewsInfoCrawSymbolService implements PageProcessor, Pipeline {

    @Autowired
    private SymbolMapper symbolMapper;

    /**
     * 解析页面
     */
    @Override
    public void process(Page page) {
        //解析html
        Request request = page.getRequest();
        String url = request.getUrl();
        if (NewsInfoEnum.SYMBOL_ZH.getUrl().equals(url) || NewsInfoEnum.SYMBOL_EN.getUrl().equals(url)) {
            List<String> list = symbolMapper.listSymbol();
            if (!CollectionUtils.isEmpty(list)) {
                for (String symbol : list) {
                    String symbolUrl = url+"/?q=symbol";
                    symbolUrl = symbolUrl.replace("symbol", symbol);
                    Request rt = new Request(symbolUrl);
                    rt.setExtras(request.getExtras());
                    rt.putExtra("symbol", symbol);
                    page.addTargetRequest(rt);
                }
            }
        } else {
            List<Selectable> searchSectionMain = page.getHtml().xpath("//div[@class='searchSectionMain']").nodes();
            if (!CollectionUtils.isEmpty(searchSectionMain)) {
                for (Selectable selectable : searchSectionMain) {
                    Selectable groupHeader = selectable.xpath("h2[@class='groupHeader']");
                    if (groupHeader.regex("资讯").match() || groupHeader.regex("News").match()) {
                        List<Selectable> articleItem = selectable.xpath("div[@class='largeTitle']/div[@class='articleItem']").nodes();
                        if (!CollectionUtils.isEmpty(articleItem)) {
                            List<NewsInfoDTO> list = new ArrayList<>();
                            for (Selectable article : articleItem) {
                                String headerPicture = article.xpath("a/img/@src").get();
                                String headerContent = article.xpath("div/a[@class='title']/text()").get();
                                String linkAddress = article.xpath("div/a[@class='title']/@href").get();
                                String publisherTime = article.xpath("div/div/time/text()").get();
                                String publisherName = article.xpath("div/div/span/text()").get();
                                NewsInfoDTO newsInfoDTO = new NewsInfoDTO();
                                newsInfoDTO.setHeaderPicture(headerPicture);
                                newsInfoDTO.setHeaderContent(headerContent);
                                newsInfoDTO.setLinkAddress(linkAddress);
                                newsInfoDTO.setPublisherTime(publisherTime);
                                newsInfoDTO.setPublisherName(publisherName);
                                if (newsInfoDTO.isNotEmpty()) {
                                    list.add(newsInfoDTO);
                                }
                            }
                            page.putField("newsInfo", list);
                        }
                    }
                }
            }
        }
    }

    /**
     * 保存数据
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        Request request = resultItems.getRequest();
        String url = request.getUrl();
        log.info("----------get page: " +url);
        NewsInfoEnum newsInfoEnum = request.getExtra("craw");
        log.info("----------enum: " +newsInfoEnum.getUrl());
        String symbol = request.getExtra("symbol");
        log.info("----------get symbol: " +symbol);
        List<NewsInfoDTO> list= resultItems.get("newsInfo");
        if (!CollectionUtils.isEmpty(list)) {
            for (NewsInfoDTO newsInfoDTO : list) {
                System.out.println(newsInfoDTO.toString());
            }
        }
        System.out.println("=================================================");
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(2000);
    }

    public void createSpider(Browser browser, Request[] requests) {
        Spider.create(this)
                .addRequest(requests)
                .addPipeline(this)
                .setDownloader(new PlayWrightDownloader(browser))
                .run();
    }
}
