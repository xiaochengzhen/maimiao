package com.example.reptile.webmagic.news;

import com.example.reptile.webmagic.news.model.NewsInfoDTO;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description 要闻
 * @date 2024/12/27 9:26
 */
public class NewsHeadlinesProcessor implements PageProcessor {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1).setSleepTime(2000)
            .addCookie("123", "234")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36")
            .setUseGzip(true)
            .addHeader("accept-language", "zh-CN,zh;q=0.9");

    @Override
    public void process(Page page) {
        //从页面发现后续的url地址来抓取
        if (page.getUrl().toString().equals("https://cn.investing.com/news/headlines")) {
            //定义如何抽取页面信息，并保存下来
            List<Selectable> nodes = page.getHtml().xpath("//div[@class='min-w-0']/div/div[2]/div").nodes();
            List<NewsInfoDTO> list = new ArrayList<>();
            for (Selectable node : nodes) {
                String headerPicture = "";
                String headerContent = node.xpath("//div/a/text()").get();
                String linkAddress  = node.xpath("//div/a/@href").get();
                String publisherTime = node.xpath("//div/div/time/@datetime").get();
                String publisherName = node.xpath("//div/div/span/text()").get();
                NewsInfoDTO newsInfoDTO = new NewsInfoDTO();
                newsInfoDTO.setHeaderPicture(headerPicture);
                newsInfoDTO.setHeaderContent(headerContent);
                newsInfoDTO.setLinkAddress(linkAddress);
                newsInfoDTO.setPublisherTime(publisherTime);
                newsInfoDTO.setPublisherName(publisherName);
                list.add(newsInfoDTO);
            }
            page.putField("newsInfo", list);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",7897,"","")));
        Spider.create(new NewsHeadlinesProcessor())
                .addUrl("https://cn.investing.com/news/headlines") //从https://qd.anjuke.com/community/开始爬取
                .addPipeline(new NewsInfoPipeline())  //使用自定义的Pipeline
                .thread(1)
                .setDownloader(httpClientDownloader)
                .run();
    }
}
