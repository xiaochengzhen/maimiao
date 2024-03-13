package com.example.reptile.webmagic.getrequest.annonation;

import com.example.reptile.webmagic.getrequest.annonation.mapper.ArticleMapper;
import com.example.reptile.webmagic.getrequest.annonation.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/28 13:35
 */
@Component("annoPipeline")
@Slf4j
public class AnnoPipeline implements PageModelPipeline<Article>{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void process(Article t, Task task) {
        log.info(t.toString());
        articleMapper.save(t);
    }


}
