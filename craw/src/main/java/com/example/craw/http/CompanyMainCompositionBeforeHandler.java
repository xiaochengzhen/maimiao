package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompositionDataDTO;
import com.example.craw.util.EncodeUtil;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.craw.http.CrawConstant.REGION_TYPE;
import static com.example.craw.http.CrawEnum.*;

/**
 * @description 主营构成详细数据的handler（us）查询请求日期参数
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyMainCompositionBeforeHandler extends CrawHandler{

    public static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-composition-data?code={code}&market={market}&marketType={marketType}&type={type}";
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private CompanyMainCompositionHandler companyMainCompositionHandler;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_MAIN_BEFORE");
    }

    //http 请求数据
    @Override
    void httpRequest(RequestDTO requestDTO) {
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("code",StringUtils.substringBefore(symbol, "."));
        map.put("market",StringUtils.substringAfter(symbol, "."));
        map.put("marketType",requestDTO.getMarketType());
        map.put("type",type);
        String quoteToken = EncodeUtil.getQuoteToken(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("quote-token", quoteToken);
        String body = restTemplateUtil.httpGet(map, httpHeaders, URL);
        requestDTO.setHttpResult(body);
    }

    //相应数据转换
    @Override
    void convertResponse(RequestDTO requestDTO) {
        String httpResult = requestDTO.getHttpResult();
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            String dataJT = jsonObject.getString("data");
            if (code == 0 && StringUtils.isNotBlank(dataJT) && !("[]").equals(dataJT)) {
                CompositionDataDTO compositionDataUsDTO = JSONObject.parseObject(httpResult, CompositionDataDTO.class);
                CompositionDataDTO.DataDTO data = compositionDataUsDTO.getData();
                if (data != null) {
                    List<CompositionDataDTO.DataDTO.ScreenDatesDTO> screenDates = data.getScreenDates();
                    if (!CollectionUtils.isEmpty(screenDates)) {
                        List<CompositionDataDTO.DataDTO.ScreenDatesDTO> collect = screenDates.stream().sorted(Comparator.comparing(CompositionDataDTO.DataDTO.ScreenDatesDTO::getDate, Comparator.reverseOrder())).collect(Collectors.toList());
                        //只解析日期，作为美股请求参数
                        for (CompositionDataDTO.DataDTO.ScreenDatesDTO screenDate : collect) {
                            Integer date = screenDate.getDate();
                            if (date != null) {
                                //先执行中文
                                CrawEnum[] crawEnums = {COMPANY_MAIN_ZH, COMPANY_MAIN_EN};
                                for (CrawEnum crawEnum : crawEnums) {
                                    RequestDTO request = new RequestDTO();
                                    request.setSymbol(requestDTO.getSymbol());
                                    request.setMarketType(requestDTO.getMarketType());
                                    request.setMarketCode(requestDTO.getMarketCode());
                                    request.setLanguage(crawEnum.getLanguage());
                                    request.setType(requestDTO.getType());
                                    request.setDate(date.toString());
                                    companyMainCompositionHandler.craw(request);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //转换好的数据存库
    @Override
    void saveData(RequestDTO requestDTO) {

    }

}
