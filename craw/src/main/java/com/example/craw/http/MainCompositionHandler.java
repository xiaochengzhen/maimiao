package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompositionDataResponseDTO;
import com.example.craw.mapper.CompanyMainCompositionMapper;
import com.example.craw.model.CompanyMainCompositionDO;
import com.example.craw.util.EncodeUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class MainCompositionHandler extends CrawHandler{

    public static final String URL = "https://www.futunn.com/en/stock/00002-HK/financial/main-composition";
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean match(CrawEnum crawEnum) {
        return crawEnum.getCode().equals("MAIN_COMPOSITION");
    }

    @Override
    void httpRequest(RequestDTO requestDTO) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL, String.class);
        String body = forEntity.getBody();
        String s = extractJsonContent(body);
        System.out.println(s);
        if (StringUtils.isNotBlank(s)) {
            String stockId = StringUtils.substringAfter(s, ":");
            if (StringUtils.isNotBlank(stockId)) {
                threadLocal.set(stockId.replace("\"",""));
            }
        }

    }

    @Override
    void convertResponse(RequestDTO requestDTO) {

    }

    @Override
    void saveData(RequestDTO requestDTO) {

    }

    private static String extractJsonContent(String input) {
        // 使用正则表达式提取 JSON 数据
        String regex = "\"stockId\":\"(\\d+)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new IllegalArgumentException("No JSON content found in the input string.");
        }
    }


}
