package com.example.javabase.stock.service;/**
 * @description
 * @author xiaobo
 * @date 2023/5/19 14:09
 */

import com.alibaba.fastjson.JSONObject;
import com.example.javabase.stock.model.StockMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @description
 * @author xiaobo
 * @date 2023/5/19 14:09
 */
@Service
public class StockService {

    @Autowired
    private RestTemplate restTemplate;
    private final static String URL = "http://gushitong.baidu.com/opendata?openapi=1&dspName=iphone&tn=tangram&client=app&query={symbol}&code={symbol}&word={symbol}&resource_id={code}&ma_ver=4&finClientType=pc";

    public String queryBaidu(String symbol, String market) {
        String date = "";
        Map<String, String> map = new HashMap<>();
        map.put("symbol", symbol);
        if (market.equals("hk")) {
            map.put("code", "5430");
        } else {
            map.put("code", "5431");
        }
        String body = restTemplate.getForObject(URL, String.class, map);
        StockMsg stockMsg = JSONObject.parseObject(body, StockMsg.class);
       // System.out.println(body);
        for (StockMsg.ResultDTO resultDTO : stockMsg.getResult()) {
            String srcID = resultDTO.getSrcID();
            if (srcID.equals("34374")) {
                System.out.println("=========1===========");
                List<StockMsg.ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO> tabs = resultDTO.getDisplayData().getResultData().getTplData().getResult().getTabs();
                for (StockMsg.ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO tab : tabs) {
                    if (tab.getType().equals("company")) {
                        System.out.println("=========2===========");
                        StockMsg.ResultDTO.DisplayDataDTO.ResultDataDTO.TplDataDTO.ResultDTO1.TabsDTO.ContentDTO content = tab.getContent();
                        if (market.equals("hk")) {
                            date= content.getNewCompany().getBasicInfo().getReleaseDate();
                            System.out.println("hk======"+date);
                        } else {
                            try {
                                date = content.getCompanyInfo().getIpoInfo().getListing().getReleaseDate();
                                System.out.println("us======"+date);
                            } catch (Exception e) {
                                date = "++";
                            }
                        }
                    }
                }
            }
        }
        return date;
    }

    public void batchGetDate() {
        Map<String, String> map = new HashMap<>();
        String[] arrayHk = {"00287","00289","00291","00294","00296","00433","00571"};
        String[] arrayUs = {"ACNT","ARTW","BRID","BSET","COHU","DOMH","DSGR","EDUC","FLXS","KEQU","LANC","MLKN","NWLI","OTTR","PATK","RAND","SMTC","TRMK","TRNS","UMBF","VIRC","WEYS","ZAZZT","ZBZZT","ZCZZT","ZJZZT","ZXYZ"};
        /*for (String s : arrayHk) {
            String date = queryBaidu(s, "hk");
            map.put(s, date);
        }*/
        for (String s : arrayUs) {
            String date = queryBaidu(s, "us");
            map.put(s, date);
        }
        map.forEach((k,v)->{
            System.out.println(k+","+v);
        });
    }

}
