package com.example.craw.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @description 
 * @author xiaobo
 * @date 2023/12/4 9:17
 */
@Slf4j
@Component
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate;

    public String httpGet(Map<String, String> map, HttpHeaders httpHeaders ,String URL) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("请求地址：{}", URL);
        log.info("请求参数：{}", JSONObject.toJSONString(map));
        HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class, map);
        log.info("响应结果：{}", exchange.getBody().substring(0, Math.min(500, exchange.getBody().length())));
        return exchange.getBody();
    }
}
