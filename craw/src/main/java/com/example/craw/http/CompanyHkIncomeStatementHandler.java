package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompanyHkIncomeStatementDTO;
import com.example.craw.dto.response.CompanyUsIncomeStatementDTO;
import com.example.craw.mapper.CompanyHkIncomeStatementMapper;
import com.example.craw.model.CompanyHkIncomeStatementDO;
import com.example.craw.model.CompanyUsIncomeStatementDO;
import com.example.craw.util.EncodeUtil;
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @description 利润表的handler
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyHkIncomeStatementHandler extends CrawHandler{

    private static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-reports-data?fetchType={fetchType}&code={code}&market={market}&quarter={quarter}&size={size}";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private CompanyHkIncomeStatementMapper companyHkIncomeStatementMapper;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_INCOME_HK") && market.equalsIgnoreCase("HK");
    }

    //http 请求数据
    @Override
    void httpRequest(RequestDTO requestDTO) {
        String language = requestDTO.getLanguage();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fetchType","2");
        map.put("code",StringUtils.substringBefore(symbol, "."));
        map.put("market", StringUtils.substringAfter(symbol, "."));
        map.put("quarter", type);
        map.put("size", "20");
        String quoteToken = EncodeUtil.getQuoteToken(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("quote-token", quoteToken);
        if (StringUtils.isBlank(language) || language.equals("en_US")) {
            String symbolMarket = StringUtils.substringBefore(symbol, ".")+"-"+StringUtils.substringAfter(symbol, ".").toUpperCase(Locale.ROOT);
            httpHeaders.add("referer", "https://www.futunn.com/en/stock/"+symbolMarket+"/financial/main-composition");
        }
        String body = restTemplateUtil.httpGet(map, httpHeaders, URL);
        requestDTO.setHttpResult(body);
    }

    //相应数据转换
    @Override
    void convertResponse(RequestDTO requestDTO) {
        List<CompanyHkIncomeStatementDO> hkList = new ArrayList<>();
        requestDTO.setConvertResult(hkList);
        String symbol = requestDTO.getSymbol();
        String httpResult = requestDTO.getHttpResult();
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            String dataJT = jsonObject.getString("data");
            if (code == 0 && StringUtils.isNotBlank(dataJT) && !("[]").equals(dataJT)) {
                CompanyHkIncomeStatementDTO companyHkIncomeStatementDTO = JSONObject.parseObject(httpResult, CompanyHkIncomeStatementDTO.class);
                CompanyHkIncomeStatementDTO.DataDTO data = companyHkIncomeStatementDTO.getData();
                if (data != null) {
                    CompanyHkIncomeStatementDTO.DataDTO.ListDTO reportsDataList = data.getList();
                    if (reportsDataList != null) {
                        List<String> title = reportsDataList.getTitle();
                        List<List<CompanyHkIncomeStatementDTO.DataDTO.ListDTO.ValuesDTO>> values = reportsDataList.getValues();
                        List<String> keys = reportsDataList.getKeys();
                        //利润表key和下标对应关系维护
                        Map<String, Integer> keyMap = new HashMap<>();
                        if (!CollectionUtils.isEmpty(keys)) {
                            for (int i = 0; i < keys.size(); i++) {
                                keyMap.put(keys.get(i), i);
                            }
                        }
                        //根据对应关系反射设置值
                        if (!CollectionUtils.isEmpty(title) && !CollectionUtils.isEmpty(keyMap)) {
                            String lastQ = getLastQ(title);
                            for (int i = 0; i < title.size(); i++) {
                                String quarter = title.get(i);
                                CompanyHkIncomeStatementDO companyHkIncomeStatementDO = new CompanyHkIncomeStatementDO();
                                companyHkIncomeStatementDO.setSymbol(symbol);
                                companyHkIncomeStatementDO.setQuarter(quarter);
                                companyHkIncomeStatementDO.setPeriod(Integer.valueOf(requestDTO.getType()));
                                List<CompanyHkIncomeStatementDTO.DataDTO.ListDTO.ValuesDTO> valuesDTOS = values.get(i);
                                Class<CompanyHkIncomeStatementDO> companyHkIncomeStatementDOClass = CompanyHkIncomeStatementDO.class;
                                Field[] declaredFields = companyHkIncomeStatementDOClass.getDeclaredFields();
                                for (Field declaredField : declaredFields) {
                                    declaredField.setAccessible(true);
                                    if (declaredField.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                                        IncomeKeyAnnotation incomeKeyAnnotation = declaredField.getAnnotation(IncomeKeyAnnotation.class);
                                        String key = incomeKeyAnnotation.value();
                                        CompanyHkIncomeStatementDTO.DataDTO.ListDTO.ValuesDTO valuesDTO = valuesDTOS.get(keyMap.get(key));
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
                                //保存指标最新数据
                                saveTotalIncome(lastQ, companyHkIncomeStatementDO, null);
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
        Object convertResult = requestDTO.getConvertResult();
        if (convertResult != null) {
            List<CompanyHkIncomeStatementDO> hkList = (List<CompanyHkIncomeStatementDO>) convertResult;
            if (!CollectionUtils.isEmpty(hkList)) {
                for (CompanyHkIncomeStatementDO companyHkIncomeStatementDO : hkList) {
                    CompanyHkIncomeStatementDO companyHkIncomeStatementDORaw = companyHkIncomeStatementMapper.selectByPrimaryKey(
                            companyHkIncomeStatementDO.getSymbol(), companyHkIncomeStatementDO.getQuarter(), Integer.valueOf(requestDTO.getType()));
                    if (companyHkIncomeStatementDORaw == null) {
                        companyHkIncomeStatementMapper.insert(companyHkIncomeStatementDO);
                    }
                }
            }
        }
    }

}
