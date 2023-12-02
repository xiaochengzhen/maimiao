package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompanyFinancialRealDTO;
import com.example.craw.mapper.CompanyFinancialRealMapper;
import com.example.craw.model.CompanyFinancialRealDO;
import com.example.craw.model.CompanyMainCompositionDO;
import com.example.craw.util.EncodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.craw.http.MainCompositionHandler.stockIdThreadLocal;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyFinancialRealHandler extends CrawHandler{

    private static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-financial-report-live?stockId={stockId}&date={date}";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyFinancialRealMapper companyFinancialRealMapper;

    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("FINANCIAL_REAL");
    }

    @Override
    void httpRequest(RequestDTO requestDTO) {
        String language = requestDTO.getLanguage();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("stockId",stockIdThreadLocal.get());
        map.put("date",getDateStr());
        String s = JSONObject.toJSONString(map);
        System.out.println(s);
        String quoteToken = EncodeUtil.getQuoteToken(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("quote-token", quoteToken);
        if (StringUtils.isBlank(language) || language.equals("en_US")) {
            String symbol = requestDTO.getSymbol();
            String symbolMarket = StringUtils.substringBefore(symbol, ".")+"-"+StringUtils.substringAfter(symbol, ".").toUpperCase(Locale.ROOT);
            httpHeaders.add("referer", "https://www.futunn.com/en/stock/"+symbolMarket+"/financial/main-composition");
        }
        HttpEntity httpEntity = new HttpEntity(map, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class, map);
        String body = exchange.getBody();
        System.out.println(body);
        requestDTO.setHttpResult(body);
    }

    @Override
    void convertResponse(RequestDTO requestDTO) {
        String httpResult = requestDTO.getHttpResult();
        List<CompanyMainCompositionDO> list = new ArrayList<>();
        requestDTO.setConvertResult(list);
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            JSONObject dataJT = jsonObject.getJSONObject("data");
            if (code == 0 && dataJT != null) {
                CompanyFinancialRealDTO companyFinancialRealDTO = JSONObject.parseObject(httpResult, CompanyFinancialRealDTO.class);
                CompanyFinancialRealDTO.DataDTO data = companyFinancialRealDTO.getData();
                if (data != null) {
                    CompanyFinancialRealDTO.DataDTO.DataModuleDTO dataModule = data.getDataModule();
                    if (dataModule != null) {
                        requestDTO.setConvertResult(dataModule);
                    }
                }
            }
        }
    }

    @Override
    void saveData(RequestDTO requestDTO) {
        CompanyFinancialRealDO companyFinancialRealDO = new CompanyFinancialRealDO();
        Object convertResult = requestDTO.getConvertResult();
        String symbol = requestDTO.getSymbol();
        String language = requestDTO.getLanguage();
        if (convertResult != null) {
            CompanyFinancialRealDTO.DataDTO.DataModuleDTO dataModule = (CompanyFinancialRealDTO.DataDTO.DataModuleDTO) convertResult;
            CompanyFinancialRealDO companyFinancialRealRaw = companyFinancialRealMapper.selectByPrimaryKey(symbol);
                if (language.equals("en_US")) {
                    enExt(companyFinancialRealRaw, companyFinancialRealDO, dataModule, symbol);
                } else {
                    zhExt(companyFinancialRealRaw, companyFinancialRealDO, dataModule, symbol);
                }
        }
    }

    private void enExt(CompanyFinancialRealDO companyFinancialRealRaw, CompanyFinancialRealDO companyFinancialRealDO, CompanyFinancialRealDTO.DataDTO.DataModuleDTO dataModule, String symbol) {
        String unit = dataModule.getUnit();
        if (StringUtils.isNotBlank(unit)) {
            String currency = StringUtils.substringAfter(unit, ":").trim();
            if (StringUtils.isNotBlank(currency)) {
                if (companyFinancialRealRaw != null && StringUtils.isBlank(companyFinancialRealRaw.getCurrency())) {
                    companyFinancialRealDO.setId(companyFinancialRealRaw.getId());
                    companyFinancialRealDO.setCurrency(currency);
                    companyFinancialRealMapper.update(companyFinancialRealDO);
                }
                if (companyFinancialRealRaw == null) {
                    companyFinancialRealDO.setSymbol(symbol);
                    companyFinancialRealDO.setCurrency(currency);
                    companyFinancialRealMapper.insert(companyFinancialRealDO);
                }
            }
        }
    }

    private void zhExt(CompanyFinancialRealDO companyFinancialRealRaw, CompanyFinancialRealDO companyFinancialRealDO, CompanyFinancialRealDTO.DataDTO.DataModuleDTO dataModule, String symbol) {
        if (dataModule != null) {
            if (companyFinancialRealRaw != null && StringUtils.isBlank(companyFinancialRealRaw.getCompositionData())) {
                companyFinancialRealDO.setId(companyFinancialRealRaw.getId());
                companyFinancialRealDO.setCompositionData(JSON.toJSONString(dataModule));
                companyFinancialRealMapper.update(companyFinancialRealDO);
            }
            if (companyFinancialRealRaw == null) {
                companyFinancialRealDO.setSymbol(symbol);
                companyFinancialRealDO.setCompositionData(JSON.toJSONString(dataModule));
                companyFinancialRealMapper.insert(companyFinancialRealDO);
            }
        }
    }

    private String getDateStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = LocalDateTime.now().format(formatter);
        return format;
    }

}