package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.HsiDTO;
import com.example.craw.mapper.SymbolGradeMapper;
import com.example.craw.model.SymbolGradeDO;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description 财务指标的handler
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class WikiHandler extends CrawHandler{

    private static final String URL = "https://en.wikipedia.org/wiki/List_of_S&P_500_companies";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private SymbolGradeMapper symbolGradeMapper;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("WIKI");
    }

    //http 请求数据
    @Override
    void httpRequest(RequestDTO requestDTO) {
        String body = restTemplateUtil.httpGetSimple(URL);
        requestDTO.setHttpResult(body);
    }

    //相应数据转换
    @Override
    void convertResponse(RequestDTO requestDTO) {
        List<SymbolGradeDO> resultList = new ArrayList<>();
        String type = requestDTO.getType();
        String httpResult = requestDTO.getHttpResult();
        if (StringUtils.isNotBlank(httpResult)) {
            if (StringUtils.isNotBlank(httpResult)) {
                Document doc= Jsoup.parse(httpResult);
                Element constituents = doc.getElementById("constituents");
                Elements elements = constituents.getElementsByClass("external text");
                if (!CollectionUtils.isEmpty(elements)) {
                    for (Element element : elements) {
                        if (element.childNodeSize() == 1 && element.childNode(0) != null) {
                            SymbolGradeDO symbolGradeDO = new SymbolGradeDO();
                            symbolGradeDO.setSymbol(element.childNode(0)+".us");
                            symbolGradeDO.setMarket("us");
                            symbolGradeDO.setType(Integer.valueOf(type));
                            resultList.add(symbolGradeDO);
                        }
                    }
                }
            }
        }
        requestDTO.setConvertResult(resultList);
    }

    //转换好的数据存库
    @Transactional
    @Override
    void saveData(RequestDTO requestDTO) {
        String type = requestDTO.getType();
        Object convertResult = requestDTO.getConvertResult();
        if (convertResult != null) {
            List<SymbolGradeDO> list = (List<SymbolGradeDO>) convertResult;
            if (!CollectionUtils.isEmpty(list)) {
                symbolGradeMapper.deleteByType(Integer.valueOf(type));
                symbolGradeMapper.insertBatch(list);
            }
        }
    }

}
