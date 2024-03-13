package com.example.reptile.webmagic.postrequest.demo;

import com.example.reptile.webmagic.getrequest.demo.model.Article;
import com.sun.net.httpserver.HttpContext;
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
public class PostProcessor implements PageProcessor {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setUserAgent("");

    @Override
    public void process(Page page) {
        Json json = page.getJson();
        String s = json.get();
        System.out.println(s);
        page.putField("json", s);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Request request = new Request("https://msg.csdn.net/v1/web/message/view/unread");
        request.addCookie("cookie", "uuid_tt_dd=10_37085562930-1649923668859-391490; UN=qq_35285375; __bid_n=1843274a36753cf7f54207; FEID=v10-674be56c147fd0a48c93a3572023fe806f9b4ddb; __xaf_fpstarttimer__=1672802894013; __xaf_thstime__=1672802894126; __xaf_fptokentimer__=1672802894166; FCNEC=%5B%5B%22AKsRol88AwHT_ak0k5hV3S17naEetEmwO53VvAL9E3PahIvBu5WfQZmwifyficuIdpHoA0DvxRVBiVNrDNs58-9luLzCgPa_uswxx4leD6f5ulSIEo4jLOnP_QvQcUAzNbaFLn5EbFiPHg0qrTFcWw1lQckLeQ4kfg%3D%3D%22%5D%2Cnull%2C%5B%5D%5D; FPTOKEN=1ElE+czfPezofEY60He3yhKLr/j/EiZAFOqj1IyOfgb6+YDq9VkvYcMWxyt+D7XYjQ58KwGZPm6EEllfURBYtHDGXtoRwbDTSCVyUDMzGitmjT6GpHfQmcsIton2+omgigXiW53KWo+rO0a3LjT36p5FVEWAYBVO9lNr4ZxZPr2VPbST2keqUkQHF2xxqGY6QSJ0iifYOGqG5TKB5ws+9KX+JOEOwAk0NU/g/7GxSbgq76O1QNOKMPlBEtgqZ/nCOvbbEfXlJyqceEEDCRauyYGHGjB0rllCYUHmzfDfXsT9nVjav3aN2+NWDwP0AED5icnCDElmbLHWO/AMp1WnIZbuFlEDB0nWslLQKxs8aW8tuFO8uwhdNPIRfmWwSvRwo0EUL+pe74Zrj+Z/Ho/aUA==|Y7ZYzMGUCGuUYtm6ge0oRUOX1AEoDDyceV163pxAmDw=|10|50d2c220fed033ec0b0e653055b17a93; UserName=qq_35285375; UserInfo=1c36303e549641078f49030f37b6c270; UserToken=1c36303e549641078f49030f37b6c270; UserNick=qq_35285375; AU=3A2; BT=1701160823427; p_uid=U010000; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22qq_35285375%22%2C%22scope%22%3A1%7D%7D; c_segment=2; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1708502547; dp_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjkxOTQ5OSwiZXhwIjoxNzEwNDg0NzUxLCJpYXQiOjE3MDk4Nzk5NTEsInVzZXJuYW1lIjoicXFfMzUyODUzNzUifQ.csNMwW5iovWfiA1R9Y_OGJm1I-XkVh5LIvNVzq5GWHk; _clck=1d09ayv%7C2%7Cfjw%7C0%7C1520; qq_35285375comment_new=1709697225097; dc_sid=53136fea75999dd484c68278711f516c; log_Id_click=423; dc_session_id=10_1710117398002.312972; c_pref=default; SESSION=ODYyNzIwMTctMWZmMC00MDJjLWJjOTUtOWZjYzljOTM2MmQ5; firstDie=1; creative_btn_mp=3; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1710119564; __gads=ID=d7405bacb6cd60f7-22c5df83a5e00085:T=1683628143:RT=1710119565:S=ALNI_Mb7kU5qYKfu8vIaigVN1pcLicJfsw; __gpi=UID=00000c32572c83d7:T=1685003769:RT=1710119565:S=ALNI_MbKWXGBh_Muz_gJtx1G2QoTmvdFbw; __eoi=ID=e6d5d8028f086928:T=1705883020:RT=1710119565:S=AA-AfjaRMcpbKJCFA34zVvrOyov-; c_ref=default; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/qq_35285375; c_dsid=11_1710119884936.188714; c_page_id=default; dc_tos=sa5sy4; log_Id_pv=830; log_Id_view=16245");
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.json("{'coupon': true}", "UTF-8"));
        Spider.create(new PostProcessor())
                .addRequest(request)
                .addPipeline(new PostPipeline())  //使用自定义的Pipeline
                .thread(5)
                .run();
    }
}
