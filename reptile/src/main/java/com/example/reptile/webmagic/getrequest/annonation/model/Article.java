package com.example.reptile.webmagic.getrequest.annonation.model;

import com.example.reptile.webmagic.getrequest.annonation.AnnoPipeline;
import lombok.Data;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/9 15:22
 */
@TargetUrl("https://blog.csdn.net/qq_35285375")
@ExtractBy(value = "//div[@class='mainContent']/div/div/div/article/a", multi = true)
@Data
public class Article {

    @ExtractBy("//div[@class='blog-img-box']/img/@src")
    private String img;

    @ExtractBy("//div[@class='list-box-cont']/div/div[@class='blog-list-box-top']/h4/text()")
    private String title;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                        , new AnnoPipeline(), Article.class)
                .addUrl("https://blog.csdn.net/qq_35285375").thread(1).run();
    }
}
