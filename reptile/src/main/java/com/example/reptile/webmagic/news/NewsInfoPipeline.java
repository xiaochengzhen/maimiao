package com.example.reptile.webmagic.news;

import com.example.reptile.webmagic.getrequest.demo.model.Article;
import com.example.reptile.webmagic.news.model.NewsInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
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
public class NewsInfoPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("----------get page: " + resultItems.getRequest().getUrl());
        List<NewsInfoDTO> list= resultItems.get("newsInfo");
        if (!CollectionUtils.isEmpty(list)) {
            for (NewsInfoDTO newsInfoDTO : list) {
                System.out.println(newsInfoDTO.toString());
            }
        }
        System.out.println("=================================================");
    }
}
