package com.example.reptile.webmagic.getrequest.annonation.model;

import com.example.reptile.webmagic.getrequest.annonation.AnnoPipeline;
import lombok.Data;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/9 15:22
 */
@TargetUrl("https://blog.csdn.net/qq_35285375/article/details/*")
@HelpUrl("https://blog.csdn.net/qq_35285375")
@Data
public class ArticleDetail {

    @ExtractBy("//div[@class='article-title-box']/h1/text()")
    private String detail;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                        , new AnnoPipeline(), ArticleDetail.class)
                .addUrl("https://blog.csdn.net/qq_35285375").thread(1).run();
    }
}
