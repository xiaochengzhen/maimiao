package com.example.craw.controller;

import com.example.craw.http.CrawEnum;
import com.example.craw.service.CrawService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @author xiaobo
 * @date 2023/11/29 9:51
 */
@RestController
public class CrawController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrawService crawService;

    @GetMapping("/craw")
    public void craw(String craw, String symbol) {
        List<CrawEnum> values = CrawEnum.getCrawEnum(1);
        if (StringUtils.isNotBlank(craw)) {
            values.add(CrawEnum.valueOf(craw));
        }
        crawService.craw(Pair.of(values, symbol));
    }

    @GetMapping("/test")
    public void test() {
        String forObject = restTemplate.getForObject("http://localhost:8080/test1", String.class);
        System.out.println(forObject);
    }

    @GetMapping("/test1")
    public String test1() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123";
    }



}
