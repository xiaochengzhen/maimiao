package com.example.reptile.webmagic.getrequest.annonation;

import com.example.reptile.webmagic.getrequest.annonation.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/9 17:44
 */
@RestController
public class AnnTest {

    @Autowired
    private AnnoPipeline annoPipeline;

    @GetMapping("/test")
    public  void test() {
        System.out.println("==============");
        OOSpider.create(Site.me().setSleepTime(1000)
                        , annoPipeline, Article.class)
                .addUrl("https://blog.csdn.net/qq_35285375").thread(1).run();
    }
}
