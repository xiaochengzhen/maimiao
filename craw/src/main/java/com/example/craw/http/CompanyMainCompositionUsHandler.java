package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompositionDataUsDTO;
import com.example.craw.mapper.CompanyMainCompositionMapper;
import com.example.craw.model.CompanyMainCompositionDO;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyMainCompositionUsHandler extends CrawHandler{

    public static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-composition-data?code={code}&market={market}&marketType={marketType}&type={type}&date={date}";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyMainCompositionMapper companyMainCompositionMapper;

    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_MAIN_US") && market.equalsIgnoreCase("US");
    }

    @Override
    void httpRequest(RequestDTO requestDTO) {
        String date = requestDTO.getDate();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        String language = requestDTO.getLanguage();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("code",StringUtils.substringBefore(symbol, "."));
        map.put("market",StringUtils.substringAfter(symbol, "."));
        map.put("marketType",requestDTO.getMarketType());
        map.put("type",type);
        map.put("date",date);
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
        String httpResult = requestDTO.getHttpResult();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        String language = requestDTO.getLanguage();
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            JSONObject dataJT = jsonObject.getJSONObject("data");
            if (code == 0 && dataJT != null) {
                CompositionDataUsDTO compositionDataUsDTO = JSONObject.parseObject(httpResult, CompositionDataUsDTO.class);
                CompositionDataUsDTO.DataDTO data = compositionDataUsDTO.getData();
                if (data != null) {
                    String date = data.getDate();
                    if (language.equals("en_US")) {
                        date = dateFormatConvert(date);
                    }
                    CompanyMainCompositionDO companyMainCompositionDO = new CompanyMainCompositionDO();
                    companyMainCompositionDO.setSymbol(symbol);
                    companyMainCompositionDO.setQuarter(date);
                    String priceItemStr = JSON.toJSONString(data);
                    if (type.equals("4")) {
                        companyMainCompositionDO.setRegion(priceItemStr);
                    } else {
                        companyMainCompositionDO.setBusiness(priceItemStr);
                    }
                    requestDTO.setConvertResult(companyMainCompositionDO);
                }
            }
        }
    }

    @Override
    void saveData(RequestDTO requestDTO) {
        Object convertResult = requestDTO.getConvertResult();
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        String language = requestDTO.getLanguage();
        if (convertResult != null) {
            CompanyMainCompositionDO companyMainCompositionDO = (CompanyMainCompositionDO) convertResult;
                CompanyMainCompositionDO companyMainCompositionDORaw = companyMainCompositionMapper.selectByQuarter(symbol, companyMainCompositionDO.getQuarter());
                if (companyMainCompositionDORaw != null) {
                    companyMainCompositionDO.setId(companyMainCompositionDORaw.getId());
                    if (language.equals("zh_CN")) {
                        if (zhExt(type, companyMainCompositionDO, companyMainCompositionDORaw)) {
                            return;
                        }
                    } else {
                        if (enExt(type, companyMainCompositionDO, companyMainCompositionDORaw)) {
                            return;
                        }
                    }
                    companyMainCompositionMapper.update(companyMainCompositionDO);
                } else {
                    if (type.equals("4")) {
                        companyMainCompositionDO.setRegion(companyMainCompositionDO.getRegion());
                    } else {
                        companyMainCompositionDO.setBusiness(companyMainCompositionDO.getBusiness());
                    }
                    companyMainCompositionMapper.insert(companyMainCompositionDO);
                }
        }
    }

    private boolean zhExt(String type, CompanyMainCompositionDO companyMainCompositionDO, CompanyMainCompositionDO companyMainCompositionDORaw) {
        if (type.equals("4")) {
            String regionRaw = companyMainCompositionDORaw.getRegion();
            if (StringUtils.isNotBlank(regionRaw)) {
                return true;
            }
        } else {
            String businessRaw = companyMainCompositionDORaw.getBusiness();
            if (StringUtils.isNotBlank(businessRaw)) {
                return true;
            }
        }
        return false;
    }

    private boolean enExt(String type, CompanyMainCompositionDO companyMainCompositionDO, CompanyMainCompositionDO companyMainCompositionDORaw) {
        Map<Integer, String> nameMap = new HashMap<>();
        String str = "";
        if (type.equals("4")) {
            str = companyMainCompositionDO.getRegion();
        } else {
            str = companyMainCompositionDO.getBusiness();
        }
        CompositionDataUsDTO.DataDTO dataDTO = JSON.parseObject(str, CompositionDataUsDTO.DataDTO.class);
        List<CompositionDataUsDTO.DataDTO.PriceItemDTO> priceItem = dataDTO.getPriceItem();
        if (!CollectionUtils.isEmpty(priceItem)) {
            for (int i = 0; i < priceItem.size(); i++) {
                CompositionDataUsDTO.DataDTO.PriceItemDTO priceItemDTO = priceItem.get(i);
                if (priceItemDTO != null) {
                    String name = priceItemDTO.getName();
                    if (StringUtils.isNotBlank(name)) {
                        name = JSON.parseObject(name).getString("zh_CN");
                    }
                    nameMap.put(i, name);
                }
            }
        }
        if (type.equals("4")) {
            String regionRaw = companyMainCompositionDORaw.getRegion();
            if (StringUtils.isBlank(regionRaw)) {
                return true;
            }
            CompositionDataUsDTO.DataDTO dataDTORaw = JSON.parseObject(regionRaw, CompositionDataUsDTO.DataDTO.class);
            List<CompositionDataUsDTO.DataDTO.PriceItemDTO> priceItemRaw = dataDTORaw.getPriceItem();
            for (int i = 0; i < priceItemRaw.size(); i++) {
                CompositionDataUsDTO.DataDTO.PriceItemDTO priceItemDTORaw = priceItemRaw.get(i);
                String name = priceItemDTORaw.getName();
                JSONObject nameJson = JSON.parseObject(name);
                nameJson.put("en_US", nameMap.get(i));
                priceItemDTORaw.setName(nameJson.toJSONString());
            }
            companyMainCompositionDO.setRegion(JSONObject.toJSONString(dataDTORaw));
        } else {
            String businessRaw = companyMainCompositionDORaw.getBusiness();
            if (StringUtils.isBlank(businessRaw)) {
                return true;
            }
            CompositionDataUsDTO.DataDTO dataDTORaw = JSON.parseObject(businessRaw, CompositionDataUsDTO.DataDTO.class);
            List<CompositionDataUsDTO.DataDTO.PriceItemDTO> priceItemRaw = dataDTORaw.getPriceItem();
            for (int i = 0; i < priceItemRaw.size(); i++) {
                CompositionDataUsDTO.DataDTO.PriceItemDTO priceItemDTORaw = priceItemRaw.get(i);
                String name = priceItemDTORaw.getName();
                JSONObject nameJson = JSON.parseObject(name);
                nameJson.put("en_US", nameMap.get(i));
                priceItemDTORaw.setName(nameJson.toJSONString());
            }
            companyMainCompositionDO.setBusiness(JSONObject.toJSONString(dataDTORaw));
        }
        return false;
    }

    public static String dateFormatConvert(String date) {
        // 定义输入日期字符串的格式
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // 定义输出日期字符串的格式
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // 解析输入日期字符串
            Date inputDate = inputDateFormat.parse(date);
            // 格式化输出日期字符串
            String outputDateStr = outputDateFormat.format(inputDate);
            return outputDateStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}
