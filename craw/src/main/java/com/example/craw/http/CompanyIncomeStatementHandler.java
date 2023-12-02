package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompanyFinancialRealDTO;
import com.example.craw.dto.response.CompanyIncomeStatementDTO;
import com.example.craw.mapper.CompanyFinancialRealMapper;
import com.example.craw.mapper.CompanyHkIncomeStatementMapper;
import com.example.craw.mapper.CompanyUsIncomeStatementMapper;
import com.example.craw.model.CompanyFinancialRealDO;
import com.example.craw.model.CompanyHkIncomeStatementDO;
import com.example.craw.model.CompanyUsIncomeStatementDO;
import com.example.craw.util.EncodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyIncomeStatementHandler extends CrawHandler{

    private static final String URL = "https://www.futunn.com/quote-api/quote-v2/public-reports-data?fetchType={fetchType}&marketType={marketType}&code={code}&marketLabel={marketLabel}";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyHkIncomeStatementMapper companyHkIncomeStatementMapper;
    @Autowired
    private CompanyUsIncomeStatementMapper companyUsIncomeStatementMapper;
    @Autowired
    private CompanyFinancialRealMapper companyFinancialRealMapper;


    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_INCOME");
    }

    @Override
    void httpRequest(RequestDTO requestDTO) {
        String language = requestDTO.getLanguage();
        String symbol = requestDTO.getSymbol();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fetchType","2");
        map.put("marketType", requestDTO.getMarketType());
        map.put("code",StringUtils.substringBefore(symbol, "."));
        map.put("marketLabel",StringUtils.substringAfter(symbol, ".").toUpperCase(Locale.ROOT));
        String s = JSONObject.toJSONString(map);
        System.out.println(s);
        String quoteToken = EncodeUtil.getQuoteToken(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("quote-token", quoteToken);
        if (StringUtils.isBlank(language) || language.equals("en_US")) {
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
        if (requestDTO.getMarketType().equals("1")) {
            hkExt(requestDTO);
        } else {
            usExt(requestDTO);
        }
    }

    @Override
    void saveData(RequestDTO requestDTO) {
        if (requestDTO.getMarketType().equals("1")) {
            hkSaveExt(requestDTO);
        } else {
            usSaveExt(requestDTO);
        }
    }


    public void hkExt(RequestDTO requestDTO) {
        List<CompanyHkIncomeStatementDO> hkList = new ArrayList<>();
        requestDTO.setConvertResult(hkList);
        String symbol = requestDTO.getSymbol();
        String httpResult = requestDTO.getHttpResult();
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            JSONObject dataJT = jsonObject.getJSONObject("data");
            if (code == 0 && dataJT != null) {
                CompanyIncomeStatementDTO companyIncomeStatementDTO = JSONObject.parseObject(httpResult, CompanyIncomeStatementDTO.class);
                CompanyIncomeStatementDTO.DataDTO data = companyIncomeStatementDTO.getData();
                if (data != null) {
                    CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO keyIndicatorsReportsData = data.getKeyIndicatorsReportsData();
                    if (keyIndicatorsReportsData != null) {
                        CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO reportsDataList = keyIndicatorsReportsData.getList();
                        if (reportsDataList != null) {
                            List<String> title = reportsDataList.getTitle();
                            List<List<CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO.ValuesDTO>> values = reportsDataList.getValues();
                            List<String> keys = reportsDataList.getKeys();
                            Map<String, Integer> keyMap = new HashMap<>();
                            if (!CollectionUtils.isEmpty(keys)) {
                                for (int i = 0; i < keys.size(); i++) {
                                    keyMap.put(keys.get(i), i);
                                }
                            }
                            if (!CollectionUtils.isEmpty(title) && !CollectionUtils.isEmpty(keyMap)) {
                                String lastQ = getLastQ(title);
                                for (int i = 0; i < title.size(); i++) {
                                    String quarter = title.get(i);
                                    CompanyHkIncomeStatementDO companyHkIncomeStatementDO = new CompanyHkIncomeStatementDO();
                                    companyHkIncomeStatementDO.setSymbol(symbol);
                                    companyHkIncomeStatementDO.setQuarter(quarter);
                                    List<CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO.ValuesDTO> valuesDTOS = values.get(i);
                                    Class<CompanyHkIncomeStatementDO> companyHkIncomeStatementDOClass = CompanyHkIncomeStatementDO.class;
                                    Field[] declaredFields = companyHkIncomeStatementDOClass.getDeclaredFields();
                                    for (Field declaredField : declaredFields) {
                                        declaredField.setAccessible(true);
                                        if (declaredField.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                                            IncomeKeyAnnotation incomeKeyAnnotation = declaredField.getAnnotation(IncomeKeyAnnotation.class);
                                            String key = incomeKeyAnnotation.value();
                                            CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO.ValuesDTO valuesDTO = valuesDTOS.get(keyMap.get(key));
                                            if (valuesDTO != null) {
                                                try {
                                                    declaredField.set(companyHkIncomeStatementDO, JSON.toJSONString(valuesDTO, SerializerFeature.WriteMapNullValue));
                                                } catch (IllegalAccessException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                    hkList.add(companyHkIncomeStatementDO);
                                    saveTotalIncome(lastQ, companyHkIncomeStatementDO, null);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void usExt(RequestDTO requestDTO) {
        List<CompanyUsIncomeStatementDO> usKist = new ArrayList<>();
        requestDTO.setConvertResult(usKist);
        String symbol = requestDTO.getSymbol();
        String httpResult = requestDTO.getHttpResult();
        if (StringUtils.isNotBlank(httpResult)) {
            CompanyIncomeStatementDTO companyIncomeStatementDTO = JSONObject.parseObject(httpResult, CompanyIncomeStatementDTO.class);
            Integer code = companyIncomeStatementDTO.getCode();
            if (code == 0) {
                CompanyIncomeStatementDTO.DataDTO data = companyIncomeStatementDTO.getData();
                if (data != null) {
                    CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO keyIndicatorsReportsData = data.getKeyIndicatorsReportsData();
                    if (keyIndicatorsReportsData != null) {
                        CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO reportsDataList = keyIndicatorsReportsData.getList();
                        if (reportsDataList != null) {
                            List<String> title = reportsDataList.getTitle();
                            List<List<CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO.ValuesDTO>> values = reportsDataList.getValues();
                            List<String> keys = reportsDataList.getKeys();
                            Map<String, Integer> keyMap = new HashMap<>();
                            if (!CollectionUtils.isEmpty(keys)) {
                                for (int i = 0; i < keys.size(); i++) {
                                    keyMap.put(keys.get(i), i);
                                }
                            }
                            if (!CollectionUtils.isEmpty(title) && !CollectionUtils.isEmpty(keyMap)) {
                                String lastQ = getLastQ(title);
                                for (int i = 0; i < title.size(); i++) {
                                    String quarter = title.get(i);
                                    CompanyUsIncomeStatementDO companyUsIncomeStatementDO = new CompanyUsIncomeStatementDO();
                                    companyUsIncomeStatementDO.setSymbol(symbol);
                                    companyUsIncomeStatementDO.setQuarter(quarter);
                                    List<CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO.ValuesDTO> valuesDTOS = values.get(i);
                                    Class<CompanyUsIncomeStatementDO> CompanyUsIncomeStatementDOClass = CompanyUsIncomeStatementDO.class;
                                    Field[] declaredFields = CompanyUsIncomeStatementDOClass.getDeclaredFields();
                                    for (Field declaredField : declaredFields) {
                                        declaredField.setAccessible(true);
                                        if (declaredField.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                                            IncomeKeyAnnotation incomeKeyAnnotation = declaredField.getAnnotation(IncomeKeyAnnotation.class);
                                            String key = incomeKeyAnnotation.value();
                                            CompanyIncomeStatementDTO.DataDTO.KeyIndicatorsReportsDataDTO.ListDTO.ValuesDTO valuesDTO = valuesDTOS.get(keyMap.get(key));
                                            if (valuesDTO != null) {
                                                try {
                                                    declaredField.set(companyUsIncomeStatementDO, JSON.toJSONString(valuesDTO, SerializerFeature.WriteMapNullValue));
                                                } catch (IllegalAccessException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                    usKist.add(companyUsIncomeStatementDO);
                                    saveTotalIncome(lastQ, null, companyUsIncomeStatementDO);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void hkSaveExt(RequestDTO requestDTO) {
        Object convertResult = requestDTO.getConvertResult();
        if (convertResult != null) {
            List<CompanyHkIncomeStatementDO> hkList = (List<CompanyHkIncomeStatementDO>) convertResult;
            if (!CollectionUtils.isEmpty(hkList)) {
                for (CompanyHkIncomeStatementDO companyHkIncomeStatementDO : hkList) {
                    CompanyHkIncomeStatementDO companyHkIncomeStatementDORaw = companyHkIncomeStatementMapper.selectByPrimaryKey(companyHkIncomeStatementDO.getSymbol(), companyHkIncomeStatementDO.getQuarter());
                    if (companyHkIncomeStatementDORaw == null) {
                        companyHkIncomeStatementMapper.insert(companyHkIncomeStatementDO);
                    }
                }
            }
        }
    }

    public void usSaveExt(RequestDTO requestDTO) {
        Object convertResult = requestDTO.getConvertResult();
        if (convertResult != null) {
            List<CompanyUsIncomeStatementDO> hkList = (List<CompanyUsIncomeStatementDO>) convertResult;
            if (!CollectionUtils.isEmpty(hkList)) {
                for (CompanyUsIncomeStatementDO companyUsIncomeStatementDO : hkList) {
                    CompanyUsIncomeStatementDO companyUsIncomeStatementDORaw = companyUsIncomeStatementMapper.selectByPrimaryKey(companyUsIncomeStatementDO.getSymbol(), companyUsIncomeStatementDO.getQuarter());
                    if (companyUsIncomeStatementDORaw == null) {
                        companyUsIncomeStatementMapper.insert(companyUsIncomeStatementDO);
                    }
                }
            }
        }
    }

    private void saveTotalIncome(String lastQ, CompanyHkIncomeStatementDO companyHkIncomeStatementDO, CompanyUsIncomeStatementDO companyUsIncomeStatementDO) {
        if (StringUtils.isBlank(lastQ)) {
            return;
        }
        String symbol = "";
        BigDecimal revenue = null;
        String quarter = "";
        String incomeRevenue = "";
        if (companyHkIncomeStatementDO != null) {
            symbol = companyHkIncomeStatementDO.getSymbol();
            incomeRevenue = companyHkIncomeStatementDO.getOperatingIncome();
            quarter = companyHkIncomeStatementDO.getQuarter();
        }
        if (companyUsIncomeStatementDO != null) {
            symbol = companyUsIncomeStatementDO.getSymbol();
            incomeRevenue = companyUsIncomeStatementDO.getTotalRevenue();
            quarter = companyUsIncomeStatementDO.getQuarter();
        }
        if (StringUtils.isNotBlank(incomeRevenue)) {
            JSONObject jsonObject = JSON.parseObject(incomeRevenue);
            revenue = jsonObject.getBigDecimal("raw_value");
        }
        if (StringUtils.isNotBlank(symbol) && StringUtils.isNotBlank(quarter) && lastQ.equals(quarter) && revenue != null) {
            CompanyFinancialRealDO companyFinancialRealRaw = companyFinancialRealMapper.selectByPrimaryKey(symbol);
            CompanyFinancialRealDO companyFinancialRealDO = new CompanyFinancialRealDO();
            companyFinancialRealDO.setRevenue(revenue);
            companyFinancialRealDO.setSymbol(symbol);
            if (companyFinancialRealRaw != null && companyFinancialRealRaw.getRevenue() == null) {
                companyFinancialRealDO.setId(companyFinancialRealRaw.getId());
                companyFinancialRealMapper.update(companyFinancialRealDO);
            }
            if (companyFinancialRealRaw == null) {
                companyFinancialRealMapper.insert(companyFinancialRealDO);
            }
        }
    }

    private String getLastQ(List<String> title) {
        if (!CollectionUtils.isEmpty(title)) {
            for (String s : title) {
                if (StringUtils.startsWith(s, "(Q")) {
                    return s;
                }
            }
        }
        return "";
    }

}
