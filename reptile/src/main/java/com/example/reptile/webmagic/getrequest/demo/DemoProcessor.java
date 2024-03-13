package com.example.reptile.webmagic.getrequest.demo;

import com.example.reptile.webmagic.getrequest.demo.model.Article;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

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
public class DemoProcessor implements PageProcessor {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setUserAgent("");
    private static int count = 0;
    private static List<String> urlList = new ArrayList<>();

    @Override
    public void process(Page page) {
        //从页面发现后续的url地址来抓取
        List<String> all = page.getHtml().xpath("//div[@class='mainContent']/div/div/div/article/a/@href").all();
        page.addTargetRequests(all);
        if (page.getUrl().toString().equals("https://blog.csdn.net/qq_35285375")) {
            //定义如何抽取页面信息，并保存下来
            List<Selectable> nodes = page.getHtml().xpath("//div[@class='mainContent']/div/div/div/article/a").nodes();
            List<Article> list = new ArrayList<>();
            for (Selectable node : nodes) {
                String s = node.xpath("//div[@class='list-box-cont']/div/div[@class='blog-list-box-top']/h4/text()").toString();
            //    System.out.println(s);
                String img = node.xpath("//div[@class='blog-img-box']/img/@src").get();
            //    System.out.println(img);
                Article article = new Article();
                article.setImg(img);
                article.setTitle(s);
                list.add(article);
            }
            page.putField("article", list);
        } else {
            String detail = page.getHtml().xpath("//div[@class='article-title-box']/h1/text()").toString();
       //     System.out.println(detail);
            page.putField("detail", detail);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new DemoProcessor())
                .addUrl("https://blog.csdn.net/qq_35285375") //从https://qd.anjuke.com/community/开始爬取
                .addPipeline(new DemoPipeline())  //使用自定义的Pipeline
                .thread(5)
                .run();
        System.out.println("----------抓取了"+count+"条记录");
    }
}
