
package com.example.craw.controller;
import com.example.craw.test.ATIRequest.SymbolDO;
import com.example.craw.test.ATIDTO.ControlRecordEnd;
import com.example.craw.test.ATIDTO.ControlRecordStart;
import com.example.craw.test.ATIDTO.DetailRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.example.craw.http.CrawEnum;
import com.example.craw.service.CrawService;
import com.example.craw.test.ATIDTO;
import com.example.craw.test.ATIRequest;
import com.example.craw.test.ATIService;
import com.example.craw.test.CcassService;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description
 * @author xiaobo
 * @date 2023/11/29 9:51
 */
@RestController
public class ATIController {

    @Autowired
    RestTemplateUtil restTemplateUtil;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/tt")
    public void tests() {
        Map map = new HashMap();
        HttpHeaders httpHeaders = new HttpHeaders();
     //   httpHeaders.add("Accept", "*/*");
     //   httpHeaders.add("Accept-Encoding", "gzip, deflate, br");
     //   httpHeaders.add("Connection", "keep-alive");
        httpHeaders.add("User-Agent", "PostmanRuntime/7.29.0");
     //   httpHeaders.add("Host", "<calculated when request is sent>");
        HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("https://www.nasdaq.com", HttpMethod.GET, httpEntity, String.class, map);
        System.out.println("");
    }

    @Autowired
    private ATIService atiService;

    @GetMapping("/ati")
    public void ati() {
        ATIRequest atiRequest = new ATIRequest();
        ArrayList<SymbolDO> symbolDOS = new ArrayList<>();
        SymbolDO symbolDO = new SymbolDO();
        symbolDO.setSymbol("00700.hk");
        symbolDO.setSecurityQuantity(100L);
        symbolDOS.add(symbolDO);
        atiRequest.setSymbols(symbolDOS);
        atiService.generateFiles(atiRequest);
    }


    @Autowired
    private CcassService ccassService;

    @GetMapping("/analysisFiles")
    public void analysisFiles() {
        ccassService.analysisFiles();
    }

}
