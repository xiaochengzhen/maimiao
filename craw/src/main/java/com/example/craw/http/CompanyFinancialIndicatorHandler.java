package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.dto.response.CompanyFinancialIndicatorDTO;
import com.example.craw.mapper.CompanyFinancialIndicatorMapper;
import com.example.craw.mapper.CompanyFinancialRealMapper;
import com.example.craw.model.CompanyFinancialIndicatorDO;
import com.example.craw.model.CompanyFinancialRealDO;
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
import java.util.*;

import static com.example.craw.http.MainCompositionHandler.stockIdThreadLocal;

/**
 * @description 
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyFinancialIndicatorHandler extends CrawHandler{

    private static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-financial-indicator?marketCode={marketCode}&stockId={stockId}&reqFinancial={reqFinancial}";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyFinancialIndicatorMapper companyFinancialIndicatorMapper;
    @Autowired
    private CompanyFinancialRealMapper companyFinancialRealMapper;

    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_FINANCIAL_INDICATOR");
    }

    @Override
    void httpRequest(RequestDTO requestDTO) {
        String language = requestDTO.getLanguage();
        String marketCode = requestDTO.getMarketCode();
        String type = requestDTO.getType();
        String levelThreeType = requestDTO.getLevelThreeType();
        Map<String, String> map = new LinkedHashMap<>();
        JSONArray jsonArray = new JSONArray();
        String[] split = StringUtils.split(levelThreeType, ",");
        for (String s : split) {
            JSONObject jsonObject = new JSONObject();
            JSONObject necessaryParameters = new JSONObject();
            necessaryParameters.put("levelOneType", 0);
            necessaryParameters.put("levelTwoType", 0);
            necessaryParameters.put("levelThreeType", Integer.parseInt(s));
            necessaryParameters.put("financialType", Integer.parseInt(type));
            jsonObject.put("necessaryParameters", necessaryParameters);
            jsonObject.put("dataCount", 20);
            jsonArray.add(jsonObject);
        }
        map.put("marketCode",marketCode);
        map.put("stockId",stockIdThreadLocal.get());
        map.put("reqFinancial",jsonArray.toJSONString());
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
        String symbol = requestDTO.getSymbol();
        String type = requestDTO.getType();
        if (StringUtils.isNotBlank(httpResult)) {
            JSONObject jsonObject = JSON.parseObject(httpResult);
            Integer code = jsonObject.getInteger("code");
            JSONObject dataJT = jsonObject.getJSONObject("data");
            if (code == 0 && dataJT != null) {
                CompanyFinancialIndicatorDTO companyFinancialIndicatorDTO = JSON.parseObject(httpResult, CompanyFinancialIndicatorDTO.class);
                CompanyFinancialIndicatorDTO.DataDTO data = companyFinancialIndicatorDTO.getData();
                if (data != null) {
                    CompanyFinancialIndicatorDO companyFinancialIndicatorDO = new CompanyFinancialIndicatorDO();
                    companyFinancialIndicatorDO.setSymbol(symbol);
                    companyFinancialIndicatorDO.setEps(data.getEps() != null ? JSON.toJSONString(data.getEps()) : null);
                    companyFinancialIndicatorDO.setBvps(data.getBvps() != null ? JSON.toJSONString(data.getBvps()) : null);
                    companyFinancialIndicatorDO.setCurrentRatio(data.getCurrentRatio() != null ? JSON.toJSONString(data.getCurrentRatio()) : null);
                    companyFinancialIndicatorDO.setQuickRatio(data.getQuickRatio() != null ? JSON.toJSONString(data.getQuickRatio()) : null);
                    companyFinancialIndicatorDO.setRoe(data.getRoe() != null ? JSON.toJSONString(data.getRoe()) : null);
                    companyFinancialIndicatorDO.setRoa(data.getRoa() != null ? JSON.toJSONString(data.getRoa()) : null);
                    companyFinancialIndicatorDO.setGrossMargin(data.getGrossMargin() != null ? JSON.toJSONString(data.getGrossMargin()) : null);
                    companyFinancialIndicatorDO.setNetMargin(data.getNetMargin() != null ? JSON.toJSONString(data.getNetMargin()) : null);
                    companyFinancialIndicatorDO.setFcf(data.getFcf() != null ? JSON.toJSONString(data.getFcf()) : null);
                    Integer period = null;
                    switch (type) {
                        case "12":
                            period = 1;
                            break;
                        case "13":
                            period = 2;
                            break;
                        case "11":
                            period = 3;
                            break;
                        default:
                            break;
                    }
                    companyFinancialIndicatorDO.setPeriod(period);
                    if (StringUtils.isNotBlank(companyFinancialIndicatorDO.getRoe()) && StringUtils.isNotBlank(companyFinancialIndicatorDO.getEps()) &&
                            StringUtils.isNotBlank(companyFinancialIndicatorDO.getCurrentRatio()) && StringUtils.isNotBlank(companyFinancialIndicatorDO.getQuickRatio()) &&
                            StringUtils.isNotBlank(companyFinancialIndicatorDO.getGrossMargin()) && StringUtils.isNotBlank(companyFinancialIndicatorDO.getBvps()) &&
                            StringUtils.isNotBlank(companyFinancialIndicatorDO.getNetMargin()) && StringUtils.isNotBlank(companyFinancialIndicatorDO.getRoa())
                            && StringUtils.isNotBlank(companyFinancialIndicatorDO.getFcf())) {
                        requestDTO.setConvertResult(companyFinancialIndicatorDO);
                    }
                }
            }
        }
    }

    @Override
    void saveData(RequestDTO requestDTO) {
        Object convertResult = requestDTO.getConvertResult();
        if (convertResult != null) {
            CompanyFinancialIndicatorDO companyFinancialIndicatorDO = (CompanyFinancialIndicatorDO) convertResult;
            CompanyFinancialIndicatorDO companyFinancialIndicatorRaw = companyFinancialIndicatorMapper.selectByPrimaryKey(companyFinancialIndicatorDO.getSymbol(), companyFinancialIndicatorDO.getPeriod());
            if (companyFinancialIndicatorRaw == null) {
                companyFinancialIndicatorMapper.insert(companyFinancialIndicatorDO);
            }
            //财务指标值用季数据的最后一条数据值
            realExt(companyFinancialIndicatorDO);
        }
    }

    private void realExt(CompanyFinancialIndicatorDO companyFinancialIndicatorDO) {
        if (companyFinancialIndicatorDO.getPeriod() == 1) {
            CompanyFinancialRealDO companyFinancialRealRaw = companyFinancialRealMapper.selectByPrimaryKey(companyFinancialIndicatorDO.getSymbol());
            CompanyFinancialRealDO companyFinancialRealDO = new CompanyFinancialRealDO();
            companyFinancialRealDO.setSymbol(companyFinancialIndicatorDO.getSymbol());
            //每股收益信息
            String eps = companyFinancialIndicatorDO.getEps();
            if (StringUtils.isNotBlank(eps)) {
                CompanyFinancialIndicatorDTO.DataDTO.Eps epsDTO = JSON.parseObject(eps, CompanyFinancialIndicatorDTO.DataDTO.Eps.class);
                List<CompanyFinancialIndicatorDTO.DataDTO.Eps.DataInfoDTO> dataInfo = epsDTO.getDataInfo();
                if (!CollectionUtils.isEmpty(dataInfo)) {
                    CompanyFinancialIndicatorDTO.DataDTO.Eps.DataInfoDTO max = dataInfo.stream().max(Comparator.comparing(CompanyFinancialIndicatorDTO.DataDTO.Eps.DataInfoDTO::getEndDate)).orElse(null);
                    companyFinancialRealDO.setEarningsPerShare(max.getIndicatorData());
                }
            }
            //净资产收益率
            String roe = companyFinancialIndicatorDO.getRoe();
            if (StringUtils.isNotBlank(roe)) {
                CompanyFinancialIndicatorDTO.DataDTO.Roe roeDTO = JSON.parseObject(roe, CompanyFinancialIndicatorDTO.DataDTO.Roe.class);
                List<CompanyFinancialIndicatorDTO.DataDTO.Roe.DataInfoDTO> dataInfo = roeDTO.getDataInfo();
                if (!CollectionUtils.isEmpty(dataInfo)) {
                    CompanyFinancialIndicatorDTO.DataDTO.Roe.DataInfoDTO max = dataInfo.stream().max(Comparator.comparing(CompanyFinancialIndicatorDTO.DataDTO.Roe.DataInfoDTO::getEndDate)).orElse(null);
                    companyFinancialRealDO.setReturnOnEquity(max.getIndicatorData());
                }
            }
            //毛利率
            String grossMargin = companyFinancialIndicatorDO.getGrossMargin();
            if (StringUtils.isNotBlank(grossMargin)) {
                CompanyFinancialIndicatorDTO.DataDTO.GrossMargin grossMarginDTO = JSON.parseObject(grossMargin, CompanyFinancialIndicatorDTO.DataDTO.GrossMargin.class);
                List<CompanyFinancialIndicatorDTO.DataDTO.GrossMargin.DataInfoDTO> dataInfo = grossMarginDTO.getDataInfo();
                if (!CollectionUtils.isEmpty(dataInfo)) {
                    CompanyFinancialIndicatorDTO.DataDTO.GrossMargin.DataInfoDTO max = dataInfo.stream().max(Comparator.comparing(CompanyFinancialIndicatorDTO.DataDTO.GrossMargin.DataInfoDTO::getEndDate)).orElse(null);
                    companyFinancialRealDO.setGrossMargin(max.getIndicatorData());
                }
            }
            //流动比率
            String currentRatio = companyFinancialIndicatorDO.getCurrentRatio();
            if (StringUtils.isNotBlank(currentRatio)) {
                CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio currentRatioDTO = JSON.parseObject(currentRatio, CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio.class);
                List<CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio.DataInfoDTO> dataInfo = currentRatioDTO.getDataInfo();
                if (!CollectionUtils.isEmpty(dataInfo)) {
                    CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio.DataInfoDTO max = dataInfo.stream().max(Comparator.comparing(CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio.DataInfoDTO::getEndDate)).orElse(null);
                    companyFinancialRealDO.setCurrentRatio(max.getIndicatorData());
                }
            }
            if (companyFinancialRealDO.getCurrentRatio() != null || companyFinancialRealDO.getGrossMargin() != null
                || companyFinancialRealDO.getReturnOnEquity() != null || companyFinancialRealDO.getEarningsPerShare() != null) {
                if (companyFinancialRealRaw != null &&
                        (companyFinancialRealRaw.getEarningsPerShare() == null) || (companyFinancialRealRaw.getReturnOnEquity() == null)
                        ||(companyFinancialRealRaw.getGrossMargin() == null) || (companyFinancialRealRaw.getCurrentRatio() == null) ) {
                    companyFinancialRealDO.setId(companyFinancialRealRaw.getId());
                    companyFinancialRealMapper.update(companyFinancialRealDO);
                }
                if (companyFinancialRealRaw == null) {
                    companyFinancialRealMapper.insert(companyFinancialRealDO);
                }
            }

        }
    }


}
