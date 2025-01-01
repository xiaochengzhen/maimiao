package com.example.reptile.webmagic.news.anno;

import com.example.reptile.webmagic.news.anno.model.NewsDTO;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/9 17:44
 */
public class NewsProcessor {

    public static void main(String[] args) {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",7897,"","")));

        Site site = Site.me().setSleepTime(1000).setTimeOut(2000)
                .addCookie("123", "234")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36")
                .setUseGzip(true)
                .addHeader("accept-language", "zh-CN,zh;q=0.9");
        OOSpider.create(site, new NewsPipeline(), NewsDTO.class)
                .setDownloader(httpClientDownloader)
                .addUrl("https://cn.investing.com/news/headlines")
                .thread(1).run();
    }
}
