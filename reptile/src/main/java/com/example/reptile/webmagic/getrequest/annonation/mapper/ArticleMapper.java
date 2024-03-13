package com.example.reptile.webmagic.getrequest.annonation.mapper;

import com.example.reptile.webmagic.getrequest.annonation.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    @Insert("insert into tb_article(img, title) values(#{img}, #{title})")
    int save(Article article);
}
