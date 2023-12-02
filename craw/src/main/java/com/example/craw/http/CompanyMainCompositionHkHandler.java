package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompositionDataHkDTO;
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
                    JSONObject jsonObject = new JSONObject();
                    if (language.equals("zh_CN")) {
                        if (type.equals("4")) {
                            jsonObject.put("zh_CN", companyMainCompositionDO.getRegion());
                            companyMainCompositionDO.setRegion(jsonObject.toJSONString());
                        } else {
                            jsonObject.put("zh_CN", companyMainCompositionDO.getBusiness());
                            companyMainCompositionDO.setBusiness(jsonObject.toJSONString());
                        }
                    } else {
                        if (type.equals("4")) {
                            jsonObject.put("en_US", companyMainCompositionDO.getRegion());
                            companyMainCompositionDO.setRegion(jsonObject.toJSONString());
                        } else {
                            jsonObject.put("en_US", companyMainCompositionDO.getBusiness());
                            companyMainCompositionDO.setBusiness(jsonObject.toJSONString());
                        }
                    }
                    companyMainCompositionMapper.insert(companyMainCompositionDO);
                }
            }
        }
    }

    private boolean zhExt(String type, CompanyMainCompositionDO companyMainCompositionDO, CompanyMainCompositionDO companyMainCompositionDORaw) {
        if (type.equals("4")) {
            String regionRaw = companyMainCompositionDORaw.getRegion();
            JSONObject regionJO = new JSONObject();
            String en = "";
            if (StringUtils.isNotBlank(regionRaw)) {
                JSONObject jsonObject = JSON.parseObject(regionRaw);
                String zh = jsonObject.getString("zh_CN");
                en = jsonObject.getString("en_US");
                if (StringUtils.isNotBlank(zh)) {
                    return true;
                }
            }
            regionJO.put("zh_CN", companyMainCompositionDO.getRegion());
            regionJO.put("en_US", en);
            companyMainCompositionDO.setRegion(regionJO.toJSONString());
        } else {
            String businessRaw = companyMainCompositionDORaw.getBusiness();
            JSONObject businessJO = new JSONObject();
            String en = "";
            if (StringUtils.isNotBlank(businessRaw)) {
                JSONObject jsonObject = JSON.parseObject(businessRaw);
                String zh = jsonObject.getString("zh_CN");
                en = jsonObject.getString("en_US");
                if (StringUtils.isNotBlank(zh)) {
                    return true;
                }
            }
            businessJO.put("zh_CN", companyMainCompositionDO.getBusiness());
            businessJO.put("en_US", en);
            companyMainCompositionDO.setBusiness(businessJO.toJSONString());
        }
        return false;
    }

    private boolean enExt(String type, CompanyMainCompositionDO companyMainCompositionDO, CompanyMainCompositionDO companyMainCompositionDORaw) {
        if (type.equals("4")) {
            String regionRaw = companyMainCompositionDORaw.getRegion();
            JSONObject regionJO = new JSONObject();
            String zh = "";
            if (StringUtils.isNotBlank(regionRaw)) {
                JSONObject jsonObject = JSON.parseObject(regionRaw);
                zh = jsonObject.getString("zh_CN");
                String en = jsonObject.getString("en_US");
                if (StringUtils.isNotBlank(en)) {
                    return true;
                }
            }
            regionJO.put("zh_CN", zh);
            regionJO.put("en_US", companyMainCompositionDO.getRegion());
            companyMainCompositionDO.setRegion(regionJO.toJSONString());
        } else {
            String businessRaw = companyMainCompositionDORaw.getBusiness();
            JSONObject businessJO = new JSONObject();
            String zh = "";
            if (StringUtils.isNotBlank(businessRaw)) {
                JSONObject jsonObject = JSON.parseObject(businessRaw);
                zh = jsonObject.getString("zh_CN");
                String en = jsonObject.getString("en_US");
                if (StringUtils.isNotBlank(en)) {
                    return true;
                }
            }
            businessJO.put("zh_CN", zh);
            businessJO.put("en_US", companyMainCompositionDO.getBusiness());
            companyMainCompositionDO.setBusiness(businessJO.toJSONString());
        }
        return false;
    }


}
