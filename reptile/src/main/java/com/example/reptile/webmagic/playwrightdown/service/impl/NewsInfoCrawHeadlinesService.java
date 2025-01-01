package com.example.reptile.webmagic.playwrightdown.service.impl;

import com.example.reptile.webmagic.news.model.NewsInfoDTO;
import com.example.reptile.webmagic.playwrightdown.service.PlayWrightDownloader;
import com.microsoft.playwright.Browser;
import lombok.extern.slf4j.Slf4j;
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
public class NewsInfoCrawHeadlinesService implements PageProcessor, Pipeline {

    /**
     * 解析页面
     */
    @Override
    public void process(Page page) {
        //解析html
        List<Selectable> nodes = page.getHtml().xpath("//div[@class='min-w-0']/div/div[2]/div").nodes();
        if (!CollectionUtils.isEmpty(nodes)) {
            List<NewsInfoDTO> list = new ArrayList<>();
            for (Selectable node : nodes) {
                String headerPicture = "";
                String headerContent = node.xpath("//div/a/text()").get();
                String linkAddress  = node.xpath("//div/a/@href").get();
                String publisherTime = node.xpath("//div/div/time/text()").get();
                String publisherName = node.xpath("//div/div/span/text()").get();
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

    /**
     * 保存数据
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("----------get page: " + resultItems.getRequest().getUrl());
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
