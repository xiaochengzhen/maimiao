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
import com.example.craw.util.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.craw.http.CrawConstant.*;
import static com.example.craw.http.MainCompositionHandler.stockIdThreadLocal;

/**
 * @description 财务指标的handler
 * @author xiaobo
 * @date 2023/11/29 8:59
 */
@Service
public class CompanyFinancialIndicatorHandler extends CrawHandler{

    private static final String URL = "https://www.futunn.com/quote-api/quote-v2/get-financial-indicator?marketCode={marketCode}&stockId={stockId}&reqFinancial={reqFinancial}";

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Autowired
    private CompanyFinancialIndicatorMapper companyFinancialIndicatorMapper;
    @Autowired
    private CompanyFinancialRealMapper companyFinancialRealMapper;

    //匹配相应的handler
    @Override
    public boolean match(CrawEnum crawEnum, String market) {
        return crawEnum.getCode().equals("COMPANY_FINANCIAL_INDICATOR");
    }

    //http 请求数据
    @Override
    void httpRequest(RequestDTO requestDTO) {
        String marketCode = requestDTO.getMarketCode();
        String type = requestDTO.getType();
        //港股没有季
        if (MARKET_CODE_HK.equals(marketCode) && QUARTER_PERIOD_REQ.equals(type)) {
            return;
        }
        String language = requestDTO.getLanguage();

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
        String quoteToken = EncodeUtil.getQuoteToken(map);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("quote-token", quoteToken);
        if (StringUtils.isBlank(language) || language.equals("en_US")) {
            String symbol = requestDTO.getSymbol();
            String symbolMarket = StringUtils.substringBefore(symbol, ".")+"-"+StringUtils.substringAfter(symbol, ".").toUpperCase(Locale.ROOT);
            httpHeaders.add("referer", "https://www.futunn.com/en/stock/"+symbolMarket+"/financial/main-composition");
        }
        String body = restTemplateUtil.httpGet(map, httpHeaders, URL);
        requestDTO.setHttpResult(body);
    }

    //相应数据转换
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
                    Set<String> quarterSet = new HashSet<>();
                    Map<String, JSONObject> epsMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.Eps eps = data.getEps();
                    if (eps != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.Eps.DataInfoDTO> dataInfo = eps.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.Eps.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, epsMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> bvpsMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.Bvps bvps = data.getBvps();
                    if (bvps != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.Bvps.DataInfoDTO> dataInfo = bvps.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.Bvps.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, bvpsMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> currentRatioMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio currentRatio = data.getCurrentRatio();
                    if (currentRatio != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio.DataInfoDTO> dataInfo = currentRatio.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.CurrentRatio.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, currentRatioMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> quickRatioMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.QuickRatio quickRatio = data.getQuickRatio();
                    if (quickRatio != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.QuickRatio.DataInfoDTO> dataInfo = quickRatio.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.QuickRatio.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, quickRatioMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> roeMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.Roe roe = data.getRoe();
                    if (roe != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.Roe.DataInfoDTO> dataInfo = roe.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.Roe.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, roeMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> roaMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.Roa roa = data.getRoa();
                    if (roa != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.Roa.DataInfoDTO> dataInfo = roa.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.Roa.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, roaMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> grossMarginMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.GrossMargin grossMargin = data.getGrossMargin();
                    if (grossMargin != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.GrossMargin.DataInfoDTO> dataInfo = grossMargin.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.GrossMargin.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, grossMarginMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> netMarginMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.NetMargin netMargin = data.getNetMargin();
                    if (netMargin != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.NetMargin.DataInfoDTO> dataInfo = netMargin.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.NetMargin.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, netMarginMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }

                    Map<String, JSONObject> fcfMap = new LinkedHashMap<>();
                    CompanyFinancialIndicatorDTO.DataDTO.Fcf fcf = data.getFcf();
                    if (fcf != null) {
                        List<CompanyFinancialIndicatorDTO.DataDTO.Fcf.DataInfoDTO> dataInfo = fcf.getDataInfo();
                        if (!CollectionUtils.isEmpty(dataInfo)) {
                            for (CompanyFinancialIndicatorDTO.DataDTO.Fcf.DataInfoDTO dataInfoDTO : dataInfo) {
                                BigDecimal indicatorData = dataInfoDTO.getIndicatorData();
                                Integer financialType = dataInfoDTO.getFinancialType();
                                BigDecimal otherIndicator = dataInfoDTO.getOtherIndicator();
                                String year = dataInfoDTO.getYear();
                                extracted(quarterSet, fcfMap, indicatorData, financialType, otherIndicator, year);
                            }
                        }
                    }
                    List<CompanyFinancialIndicatorDO> list = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(quarterSet)) {
                        List<String> quarterList = quarterSet.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                        for (String quarter : quarterList) {
                            CompanyFinancialIndicatorDO companyFinancialIndicatorDO = new CompanyFinancialIndicatorDO();
                            companyFinancialIndicatorDO.setSymbol(symbol);
                            companyFinancialIndicatorDO.setEps(epsMap.get(quarter)==null?null:epsMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setBvps(bvpsMap.get(quarter)==null?null:bvpsMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setCurrentRatio(currentRatioMap.get(quarter)==null?null:currentRatioMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setQuickRatio(quickRatioMap.get(quarter)==null?null:quickRatioMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setRoe(roeMap.get(quarter)==null?null:roeMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setRoa(roaMap.get(quarter)==null?null:roaMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setGrossMargin(grossMarginMap.get(quarter)==null?null:grossMarginMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setNetMargin(netMarginMap.get(quarter)==null?null:netMarginMap.get(quarter).toJSONString());
                            companyFinancialIndicatorDO.setFcf(fcfMap.get(quarter)==null?null:fcfMap.get(quarter).toJSONString());
                            Integer period = null;
                            switch (type) {
                                case QUARTER_PERIOD_REQ:
                                    period = QUARTER_PERIOD;
                                    break;
                                case ACCMULATED_QUARTER_PERIOD_REQ:
                                    period = ACCMULATED_QUARTER_PERIOD;
                                    break;
                                case YEAR_PERIOD_REQ:
                                    period = YEAR_PERIOD;
                                    break;
                                default:
                                    break;
                            }
                            companyFinancialIndicatorDO.setPeriod(period);
                            companyFinancialIndicatorDO.setQuarter(quarter);
                            list.add(companyFinancialIndicatorDO);
                        }
                        requestDTO.setConvertResult(list);
                    }
                }
            }
        }
    }

    private void extracted(Set<String> quarterSet, Map<String, JSONObject> epsMap, BigDecimal indicatorData, Integer financialType, BigDecimal otherIndicator, String year) {
        if ((indicatorData != null || otherIndicator != null) && financialType != null && StringUtils.isNotBlank(year)) {
            JSONObject epsOT = new JSONObject();
            epsOT.put("raw_value", indicatorData);
            epsOT.put("ratio", otherIndicator ==null?null: otherIndicator.setScale(2, RoundingMode.HALF_UP).toPlainString()+"%");
            String quarter = getQuarter(year, financialType);
            epsMap.put(quarter ,epsOT);
            quarterSet.add(quarter);
        }
    }

    //转换好的数据存库
    @Override
    void saveData(RequestDTO requestDTO) {
        Object convertResult = requestDTO.getConvertResult();
        if (convertResult != null) {
            List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOList = (List<CompanyFinancialIndicatorDO>) convertResult;
            for (CompanyFinancialIndicatorDO companyFinancialIndicatorDO : companyFinancialIndicatorDOList) {
                CompanyFinancialIndicatorDO companyFinancialIndicatorRaw = companyFinancialIndicatorMapper.selectByPrimaryKey(
                        companyFinancialIndicatorDO.getSymbol(), companyFinancialIndicatorDO.getPeriod(), companyFinancialIndicatorDO.getQuarter());
                //保存财务指标详情
                if (companyFinancialIndicatorRaw == null) {
                    companyFinancialIndicatorMapper.insert(companyFinancialIndicatorDO);
                } else {
                    companyFinancialIndicatorDO.setId(companyFinancialIndicatorRaw.getId());
                    companyFinancialIndicatorMapper.updateByPrimaryKeySelective(companyFinancialIndicatorDO);
                }
            }
            if (MARKET_CODE_US.equals(requestDTO.getMarketCode()) && QUARTER_PERIOD_REQ.equals(requestDTO.getType())) {
                //财务指标值用季数据的最后一条数据值
                realExt(companyFinancialIndicatorDOList, requestDTO.getSymbol());
            }
            if (MARKET_CODE_HK.equals(requestDTO.getMarketCode()) && ACCMULATED_QUARTER_PERIOD_REQ.equals(requestDTO.getType())){
                //财务指标值用季数据的最后一条数据值
                realExt(companyFinancialIndicatorDOList, requestDTO.getSymbol());
            }
        }
    }

    private void realExt(List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOList, String symbol) {
        CompanyFinancialRealDO companyFinancialRealRaw = companyFinancialRealMapper.selectByPrimaryKey(symbol);
        CompanyFinancialRealDO companyFinancialRealDO = new CompanyFinancialRealDO();
        companyFinancialRealDO.setSymbol(symbol);
        //每股收益信息
        BigDecimal earningsPerShare = getEarningsPerShare(companyFinancialIndicatorDOList);
        companyFinancialRealDO.setEarningsPerShare(earningsPerShare);
        //净资产收益率
        BigDecimal returnOnEquity = getReturnOnEquity(companyFinancialIndicatorDOList);
        companyFinancialRealDO.setReturnOnEquity(returnOnEquity);
        //毛利率
        BigDecimal grossMargin = getGrossMargin(companyFinancialIndicatorDOList);
        companyFinancialRealDO.setGrossMargin(grossMargin);
        //流动比率
        BigDecimal currentRatio = getCurrentRatio(companyFinancialIndicatorDOList);
        companyFinancialRealDO.setCurrentRatio(currentRatio);
        //如果有最新的财务指标，保存财务指标最新数据
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

    ////每股收益信息
    private BigDecimal getEarningsPerShare(List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOList) {
        for (CompanyFinancialIndicatorDO companyFinancialIndicatorDO : companyFinancialIndicatorDOList) {
            String eps = companyFinancialIndicatorDO.getEps();
            if (StringUtils.isNotBlank(eps)) {
                JSONObject epsDTO = JSON.parseObject(eps);
                BigDecimal rawValue = epsDTO.getBigDecimal("raw_value");
                if (rawValue != null) {
                    return rawValue;
                }
            }
        }
        return null;
    }

    //净资产收益率
    private BigDecimal getReturnOnEquity(List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOList) {
        for (CompanyFinancialIndicatorDO companyFinancialIndicatorDO : companyFinancialIndicatorDOList) {
            String roe = companyFinancialIndicatorDO.getRoe();
            if (StringUtils.isNotBlank(roe)) {
                JSONObject roeDTO = JSON.parseObject(roe);
                BigDecimal rawValue = roeDTO.getBigDecimal("raw_value");
                if (rawValue != null) {
                    return rawValue;
                }
            }
        }
        return null;
    }

    //毛利率
    private BigDecimal getGrossMargin(List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOList) {
        for (CompanyFinancialIndicatorDO companyFinancialIndicatorDO : companyFinancialIndicatorDOList) {
            String grossMargin = companyFinancialIndicatorDO.getGrossMargin();
            if (StringUtils.isNotBlank(grossMargin)) {
                JSONObject grossMarginDTO = JSON.parseObject(grossMargin);
                BigDecimal rawValue = grossMarginDTO.getBigDecimal("raw_value");
                if (rawValue != null) {
                    return rawValue;
                }
            }
        }
        return null;
    }

    //流动比率
    private BigDecimal getCurrentRatio(List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOList) {
        for (CompanyFinancialIndicatorDO companyFinancialIndicatorDO : companyFinancialIndicatorDOList) {
            String currentRatio = companyFinancialIndicatorDO.getCurrentRatio();
            if (StringUtils.isNotBlank(currentRatio)) {
                JSONObject currentRatioDTO = JSON.parseObject(currentRatio);
                BigDecimal rawValue = currentRatioDTO.getBigDecimal("raw_value");
                if (rawValue != null) {
                    return rawValue;
                }
            }
        }
        return null;
    }

    //获取周期
    private String getQuarter(String year, Integer financialType) {
        String str = "";
        switch (financialType) {
            case 1:
                str = "Q1";
                break;
            case 2:
                str = "Q2";
                break;
            case 3:
                str = "Q3";
                break;
            case 4:
                str = "Q4";
                break;
            case 5:
                str = "H1";
                break;
            case 6:
                str = "FY";
                break;
            default:
                break;
        }
        return year+"/"+str;
    }

}
