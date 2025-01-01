package com.example.reptile.webmagic.news.anno.model;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/25 18:57
 */
@Data
@TargetUrl("https://cn.investing.com/news/headlines")
@ExtractBy(value = "//div[@class='min-w-0']/div/div[2]/div", multi = true)
public class NewsDTO {
    private String headerPicture;
    @ExtractBy("//div/a/text()")
    private String headerContent;
    @ExtractBy("//div/a/@href")
    private String linkAddress;
    @ExtractBy("//div/div/time/@datetime")
    private String publisherTime;
    @ExtractBy("//div/div/span/text()")
    private String publisherName;

}
