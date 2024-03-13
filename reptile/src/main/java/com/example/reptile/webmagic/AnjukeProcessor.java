package com.example.reptile.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
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
public class AnjukeProcessor implements PageProcessor {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent("");
    private static int count = 0;
    private static List<String> urlList = new ArrayList<>();

    @Override
    public void process(Page page) {
        //从页面发现后续的url地址来抓取
        page.addTargetRequests(
                page.getHtml().xpath("//div[@class='page-content']/div[@class='multi-page']/a/@href").all());
        //判断链接是否符合"https://qd.anjuke.com/community/p任意个数字"格式
        if (page.getUrl().regex("https://qd.anjuke.com/community/p[0-9]+").match()) {
            //定义如何抽取页面信息，并保存下来
            List<Selectable> selectableList = page.getHtml().xpath("//div[@class='list-content']/div[@class='li-itemmod']").nodes();
            List<HousePrice> list = new ArrayList<>();
            for(Selectable selectable : selectableList){
                String name = selectable.xpath("//div[@class='li-info']/h3/a/text()").toString();
                String price = selectable.xpath("//div[@class='li-side']/p[1]/strong/text()").toString();
                HousePrice housePrice = new HousePrice();
                housePrice.setName(name.trim());
                housePrice.setPriceStr(price.trim());
                list.add(housePrice);
            }
            page.putField("housePriceList",list);
            urlList.add(page.getUrl().toString());
            count++;
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new AnjukeProcessor())
                .addUrl("https://qd.anjuke.com/community/") //从https://qd.anjuke.com/community/开始爬取
                .addPipeline(new HousePricePipeline())  //使用自定义的Pipeline
                .thread(5)
                .run();
        System.out.println("----------抓取了"+count+"条记录");

        Request request = new Request("http://xxx/path");
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.json("{'id':1}","utf-8"));
        Spider.create(new AnjukeProcessor())
                .addRequest(request)
                .addPipeline(new HousePricePipeline())  //使用自定义的Pipeline
                .thread(5)
                .run();
        System.out.println("----------抓取了"+count+"条记录");
    }
}
