package com.eb.rediseb.controller;

import com.eb.rediseb.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sec.common.core.vo.QuoteDataVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/1/3 15:21
 */
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @RequestMapping("/testRedis")
    public void testRedis() {
        Stock stock = new Stock();
        stock.setCount(1);
        Double d = 123.22;
        stock.setPrice(new BigDecimal(d));
        stock.setSymbol("a");
        redisTemplate.opsForValue().set("xxt", stock);

        Object xxt = redisTemplate.opsForValue().get("xxt");
        Stock stock1 = (Stock) xxt;
        System.out.println(stock1.toString());
    }


    @RequestMapping("/testRedis1")
    public void testRedis1() {
        Object xxt = redisTemplate.opsForValue().get("dev:quote-ws:META.us_SYMBOL_QUOTE");
        QuoteDataVO stock12 = (QuoteDataVO) xxt;
        System.out.println(stock12.toString());
       String[] ss = {"00072.hk","02390.hk","META.us","MSFT.us","GOOG.us","AMZN.us","BIDU.us","00020.hk",
        "BABA.us","PDD.us","JD.us","NTES.us","BIDU.us","TCOM.us","YUMC.us","ZTO.us","BGNE.us","LI.us","NIO.us","BEKE.us","HTHT.us","TME.us","FUTU.us","YMM.us",
               "BILI.us","XPEV.us","BZ.us","VIPS.us","EDU.us",
        "00700.hk","09899.hk","02400.hk","09961.hk","01810.hk","09898.hk","09999.hk","00780.hk","00020.hk","02518.hk","01024.hk","09626.hk","09888.hk","09988.hk","00241.hk",
        "TSLA.us","NIO.us","NKLA.us","LCID.us","09868.hk","02015.hk","01211.hk","09866.hk","09863.hk"};
       List<String> list = new ArrayList<>();
       for (String s : ss) {
           list.add("dev:quote-ws:"+s+"_SYMBOL_QUOTE");
        }
        List list1 = redisTemplate.opsForValue().multiGet(list);
        for (Object o : list1) {
            QuoteDataVO stock1 = (QuoteDataVO) o;
            System.out.println(stock1.toString());
        }

    }

}