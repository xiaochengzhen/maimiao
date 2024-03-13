package com.example.reptile.webmagic.postrequest.annonation;

import com.example.reptile.webmagic.getrequest.annonation.mapper.ArticleMapper;
import com.example.reptile.webmagic.getrequest.annonation.model.Article;
import com.example.reptile.webmagic.postrequest.annonation.model.PostResult;
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
@Slf4j
public class PostAnnoPipeline  implements PageModelPipeline<PostResult>{

    @Override
    public void process(PostResult t, Task task) {
        log.info(t.toString());
    }


}
