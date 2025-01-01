package com.example.reptile.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 *
 *1、Downloader：Downloader负责从互联网上下载页面，以便后续处理。WebMagic默认使用了Apache HttpClient作为下载工具。
 *2、PageProcessor：PageProcessor负责解析页面，抽取有用信息，以及发现新的链接。WebMagic使用Jsoup作为HTML解析工具，并基于其开发了解析XPath的工具Xsoup。在这四个组件中，PageProcessor对于每个站点每个页面都不一样，是需要使用者定制的部分。
 *3、Scheduler：Scheduler负责管理待抓取的URL，以及一些去重的工作。WebMagic默认提供了JDK的内存队列来管理URL，并用集合来进行去重。也支持使用Redis进行分布式管理。除非项目有一些特殊的分布式需求，否则无需自己定制Scheduler。
 *4、Pipeline：Pipeline负责抽取结果的处理，包括计算、持久化到文件、数据库等。WebMagic默认提供了“输出到控制台”和“保存到文件”两种结果处理方案。Pipeline定义了结果保存的方式，如果你要保存到指定数据库，则需要编写对应的Pipeline。对于一类需求一般只需编写一个Pipeline。
 *
 * @author xiaobo
 * @date 2024/2/28 13:34
 */
public class PostProcessor implements PageProcessor {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setRetryTimes(1).setTimeOut(5000).setSleepTime(100)
            .addHeader("accept-language", "zh-CN,zh;q=0.9")
            .addCookie("123", "234")
           // .addHeader("cookie", "t_gid=4659ee78-9d34-4b5f-9fea-fd91d67c4a62-tuct98624b6; t_pt_gid=4659ee78-9d34-4b5f-9fea-fd91d67c4a62-tuct98624b6; receive-cookie-deprecation=1")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36")
            ;
    private static int count = 0;
    private static List<String> urlList = new ArrayList<>();

    @Override
    public void process(Page page) {
        //从页面发现后续的url地址来抓取
        Json json = page.getJson();
        String s = json.get();
        System.out.println("============================"+s);
        page.putField("housePriceList",s);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",7897,"","")));
       // Request request = new Request("https://www.hsi.com.hk/data/schi/rt/index-series/hsi/constituents.do");
        Request request = new Request("https://cn.investing.com/news/headlines");
        request.setMethod(HttpConstant.Method.GET);
      //  request.setRequestBody(HttpRequestBody.json("{'id':1}","utf-8"));
        Spider.create(new PostProcessor())
                .addRequest(request)
                .addPipeline(new HousePricePipeline())  //使用自定义的Pipeline
                .thread(1)
         //       .setDownloader(httpClientDownloader)
                .run();
        System.out.println("----------抓取了"+count+"条记录");

    }
}
