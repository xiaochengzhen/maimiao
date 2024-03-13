package com.example.reptile.webmagic.getrequest.demo;

import com.example.reptile.webmagic.getrequest.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/28 13:35
 */
@Slf4j
public class DemoPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("----------get page: " + resultItems.getRequest().getUrl());
        List<Article> articles= resultItems.get("article");
        if (articles != null) {
            for (Article article : articles) {
               // log.info("list###################"+article.toString());
            }
        }
        System.out.println("=================================================");
        String detail = resultItems.get("detail");
        if (detail != null){
            //log.info("detail================================"+detail);
        }
    }
}
