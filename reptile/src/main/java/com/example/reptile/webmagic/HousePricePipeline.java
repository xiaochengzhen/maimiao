package com.example.reptile.webmagic;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/28 13:35
 */
public class HousePricePipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("----------get page: " + resultItems.getRequest().getUrl());
       // List<HousePrice> list = resultItems.get("housePriceList");
        String s = resultItems.get("housePriceList");
        System.out.println("----------list size:" + s);
    }
}
