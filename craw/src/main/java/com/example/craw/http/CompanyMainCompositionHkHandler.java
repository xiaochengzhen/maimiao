package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompositionDataHkDTO;
import com.example.craw.dto.response.CompositionDataHkDTO.DataDTO.MainIncomeDTO.PriceItemDTO;
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

import java.time.Instant;
import java.util.*;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyMainCompositionHkHandler extends CrawHandler{

    public static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-composition-data?code={code}&market={market}&marketType={marketType}&period={period}&flag={flag}&count={count}&sort={sort}&type={type}&time={time}";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyMainCompositionMapper companyMainCompositionMapper;

    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_MAIN_HK") && market.equalsIgnoreCase("HK");
    }

    @Override
    void httpRequest(RequestDTO requestDTO) {
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        String language = requestDTO.getLanguage();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("code",StringUtils.substringBefore(symbol, "."));
        map.put("market",StringUtils.substringAfter(symbol, "."));
        map.put("marketType",requestDTO.getMarketType());
        map.put("period","3");
        map.put("flag","0");
        map.put("count","100");
        map.put("sort","0");
        map.put("type",type);
        map.put("time",Instant.now().getEpochSecond()+"");
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
        List<CompanyMainCompositionDO> list = new ArrayList<>();
        requestDTO.setConvertResult(list);
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            JSONObject dataJT = jsonObject.getJSONObject("data");
            if (code == 0 && dataJT != null) {
                CompositionDataHkDTO compositionDataHkDTO = JSONObject.parseObject(httpResult, CompositionDataHkDTO.class);
                CompositionDataHkDTO.DataDTO data = compositionDataHkDTO.getData();
                if (data != null) {
                    List<CompositionDataHkDTO.DataDTO.MainIncomeDTO> mainIncome = data.getMainIncome();
                    if (!CollectionUtils.isEmpty(mainIncome)) {
                        for (CompositionDataHkDTO.DataDTO.MainIncomeDTO mainIncomeDTO : mainIncome) {
                            String period = mainIncomeDTO.getPeriod();
                            CompanyMainCompositionDO companyMainCompositionDO = new CompanyMainCompositionDO();
                            companyMainCompositionDO.setSymbol(symbol);
                            companyMainCompositionDO.setQuarter(period);
                            String mainIncomeDTOStr = JSON.toJSONString(mainIncomeDTO);
                            if (type.equals("4")) {
                                companyMainCompositionDO.setRegion(mainIncomeDTOStr);
                            } else {
                                companyMainCompositionDO.setBusiness(mainIncomeDTOStr);
                            }
                            list.add(companyMainCompositionDO);
                        }
                    }
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
            List<CompanyMainCompositionDO> list = (List<CompanyMainCompositionDO>) convertResult;
            for (CompanyMainCompositionDO companyMainCompositionDO : list) {
                CompanyMainCompositionDO companyMainCompositionDORaw = companyMainCompositionMapper.selectByQuarter(symbol, companyMainCompositionDO.getQuarter());
                if (companyMainCompositionDORaw != null) {
                    companyMainCompositionDO.setId(companyMainCompositionDORaw.getId());
                    if (language.equals("zh_CN")) {
                        if (zhExt(type, companyMainCompositionDO, companyMainCompositionDORaw)) {
                            break;
                        }
                    } else {
                        if (enExt(type, companyMainCompositionDO, companyMainCompositionDORaw)) {
                            break;
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
        CompositionDataHkDTO.DataDTO.MainIncomeDTO mainIncomeDTO = JSON.parseObject(str, CompositionDataHkDTO.DataDTO.MainIncomeDTO.class);
        List<PriceItemDTO> priceItem = mainIncomeDTO.getPriceItem();
        if (!CollectionUtils.isEmpty(priceItem)) {
            for (int i = 0; i < priceItem.size(); i++) {
                PriceItemDTO priceItemDTO = priceItem.get(i);
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
            CompositionDataHkDTO.DataDTO.MainIncomeDTO mainIncomeDTORaw = JSON.parseObject(regionRaw, CompositionDataHkDTO.DataDTO.MainIncomeDTO.class);
            List<PriceItemDTO> priceItemRaw = mainIncomeDTORaw.getPriceItem();
            for (int i = 0; i < priceItemRaw.size(); i++) {
                PriceItemDTO priceItemDTORaw = priceItemRaw.get(i);
                String name = priceItemDTORaw.getName();
                JSONObject nameJson = JSON.parseObject(name);
                nameJson.put("en_US", nameMap.get(i));
                priceItemDTORaw.setName(nameJson.toJSONString());
            }
            companyMainCompositionDO.setRegion(JSONObject.toJSONString(mainIncomeDTORaw));
        } else {
            String businessRaw = companyMainCompositionDORaw.getBusiness();
            if (StringUtils.isBlank(businessRaw)) {
                return true;
            }
            CompositionDataHkDTO.DataDTO.MainIncomeDTO mainIncomeDTORaw = JSON.parseObject(businessRaw, CompositionDataHkDTO.DataDTO.MainIncomeDTO.class);
            List<PriceItemDTO> priceItemRaw = mainIncomeDTORaw.getPriceItem();
            for (int i = 0; i < priceItemRaw.size(); i++) {
                PriceItemDTO priceItemDTORaw = priceItemRaw.get(i);
                String name = priceItemDTORaw.getName();
                JSONObject nameJson = JSON.parseObject(name);
                nameJson.put("en_US", nameMap.get(i));
                priceItemDTORaw.setName(nameJson.toJSONString());
            }
            companyMainCompositionDO.setBusiness(JSONObject.toJSONString(mainIncomeDTORaw));
        }
        return false;
    }

}
