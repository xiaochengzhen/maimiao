package com.example.reptile.webmagic.news.anno;

import com.example.reptile.webmagic.news.anno.model.NewsDTO;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/28 13:35
 */
@Slf4j
public class NewsPipeline implements PageModelPipeline<NewsDTO>{

    @Override
    public void process(NewsDTO newsInfoDTO, Task task) {
        System.out.println(newsInfoDTO.toString());
    }
}
