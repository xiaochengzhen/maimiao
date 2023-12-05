package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.SpiderResModel;
import com.example.craw.dto.query.ListCompanyIncomeStatementQuery;
import com.example.craw.dto.vo.ListSingleIncomeStatementVO;
import com.example.craw.http.IncomeKeyAnnotation;
import com.example.craw.mapper.CompanyHkIncomeStatementMapper;
import com.example.craw.mapper.CompanyUsIncomeStatementMapper;
import com.example.craw.model.CompanyHkIncomeStatementDO;
import com.example.craw.model.CompanyUsIncomeStatementDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description 利润表的service
 * @author xiaobo
 * @date 2023/12/4 13:50
 */
@Service
public class CompanyIncomeStatementService {

    private static final int SIZE = 5;
    @Autowired
    private CompanyHkIncomeStatementMapper companyHkIncomeStatementMapper;

    @Autowired
    private CompanyUsIncomeStatementMapper companyUsIncomeStatementMapper;

    /**
     * @description 查询单个利润表指标
     * @author xiaobo
     * @date 2023/12/4 14:22
     */
    public List<ListSingleIncomeStatementVO> listSingleIncomeStatement(ListCompanyIncomeStatementQuery listCompanyIncomeStatementQuery) {
        Integer incomeType = listCompanyIncomeStatementQuery.getIncomeType();
        Integer period = listCompanyIncomeStatementQuery.getPeriod();
        String symbol = listCompanyIncomeStatementQuery.getSymbol();
        String market = StringUtils.substringAfter(symbol, ".");
        List<ListSingleIncomeStatementVO> list = new ArrayList<>();
        if (market.equals("us")) {
            list = getSingleUS(incomeType, symbol, period);
        } else {
            list = getSingleHK(incomeType, symbol, period);
        }

        return list;
    }


    /**
     * @description 查询利润表详情
     * @author xiaobo
     * @date 2023/12/4 14:22
     */
    public SpiderResModel list(ListCompanyIncomeStatementQuery listCompanyIncomeStatementQuery, String language) {
        SpiderResModel spiderResModel = new SpiderResModel();
        Integer period = listCompanyIncomeStatementQuery.getPeriod();
        String symbol = listCompanyIncomeStatementQuery.getSymbol();
        String market = StringUtils.substringAfter(symbol, ".");
        //key==dataList
        Map<String, List<SpiderResModel.DataModel>> dataListMap = new HashMap<>();
        //key==title
        Map<String, String> titleMap = getTitleMap(language, market);
        //date list
        List<String> dateList = new ArrayList<>();
        if (market.equals("us")) {
            dateList = extractedUS(period, symbol, dataListMap);
        } else {
            dateList = extractedHK(period, symbol, dataListMap);
        }
        if (!CollectionUtils.isEmpty(dateList)) {
            spiderResModel.setDateList(dateList);
            List<String> titleList = new ArrayList<>();
            List<List<SpiderResModel.DataModel>> dataList = new ArrayList<>();
            spiderResModel.setTitleList(titleList);
            spiderResModel.setData(dataList);
            titleMap.forEach((k,v)->{
                titleList.add(v);
                List<SpiderResModel.DataModel> dataModels = dataListMap.get(k);
                dataList.add(dataModels);
            });
        }
        return spiderResModel;
    }

    //处理美股数据
    private Map<String, String>  getTitleMap(String language, String market) {
        Map<String, String> titleMap = new LinkedHashMap<>();
        Class clazz = null;
        if (market.equals("us")) {
             clazz = CompanyUsIncomeStatementDO.class;
        } else {
            clazz = CompanyHkIncomeStatementDO.class;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                IncomeKeyAnnotation annotation = field.getAnnotation(IncomeKeyAnnotation.class);
                String value = annotation.value();
                String title = "";
                if ("zh_CN".equals(language)) {
                    title = annotation.zhName();
                } else {
                    title = annotation.enName();
                }
                titleMap.put(value, title);
            }
        }
        return titleMap;
    }

    //处理美股数据
    private  List<String> extractedUS(Integer period, String symbol, Map<String, List<SpiderResModel.DataModel>> dataListMap) {
        List<String> dateList = new ArrayList<>();
        List<CompanyUsIncomeStatementDO> companyUsIncomeStatementDOS = companyUsIncomeStatementMapper.listBySymbol(symbol);
        if (!CollectionUtils.isEmpty(companyUsIncomeStatementDOS)) {
            List<String> collect = companyUsIncomeStatementDOS.stream().map(CompanyUsIncomeStatementDO::getQuarter).collect(Collectors.toList());
            dateList = listDate(period, collect);
            Map<String, CompanyUsIncomeStatementDO> dateDataMap = companyUsIncomeStatementDOS.stream().collect(Collectors.toMap(s -> s.getQuarter(), s -> s));
            for (String date : dateList) {
                CompanyUsIncomeStatementDO companyUsIncomeStatementDO = dateDataMap.get(date);
                Class<CompanyUsIncomeStatementDO> companyUsIncomeStatementDOClass = CompanyUsIncomeStatementDO.class;
                Field[] declaredFields = companyUsIncomeStatementDOClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    if (declaredField.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                        IncomeKeyAnnotation annotation = declaredField.getAnnotation(IncomeKeyAnnotation.class);
                        String value = annotation.value();
                        try {
                            String str = (String) declaredField.get(companyUsIncomeStatementDO);
                            if (StringUtils.isNotBlank(str)) {
                                SpiderResModel.DataModel dataModel = getDataModel(str);
                                List<SpiderResModel.DataModel> dataModels = dataListMap.get(value);
                                if (!CollectionUtils.isEmpty(dataModels)) {
                                    dataModels.add(dataModel);
                                } else {
                                    List<SpiderResModel.DataModel> dataModelList = new ArrayList<>();
                                    dataModelList.add(dataModel);
                                    dataListMap.put(value, dataModelList);
                                }
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return dateList;
    }

    //处理港股数据
    private List<String> extractedHK(Integer period, String symbol, Map<String, List<SpiderResModel.DataModel>> dataListMap) {
        List<String> dateList = new ArrayList<>();
        List<CompanyHkIncomeStatementDO> companyHkIncomeStatementDOS = companyHkIncomeStatementMapper.listBySymbol(symbol);
        if (!CollectionUtils.isEmpty(companyHkIncomeStatementDOS)) {
            List<String> collect = companyHkIncomeStatementDOS.stream().map(CompanyHkIncomeStatementDO::getQuarter).collect(Collectors.toList());
            dateList = listDate(period, collect);
            Map<String, CompanyHkIncomeStatementDO> dateDataMap = companyHkIncomeStatementDOS.stream().collect(Collectors.toMap(s -> s.getQuarter(), s -> s));
            for (String date : dateList) {
                CompanyHkIncomeStatementDO companyHkIncomeStatementDO = dateDataMap.get(date);
                Class<CompanyHkIncomeStatementDO> companyHkIncomeStatementDOClass = CompanyHkIncomeStatementDO.class;
                Field[] declaredFields = companyHkIncomeStatementDOClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    if (declaredField.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                        IncomeKeyAnnotation annotation = declaredField.getAnnotation(IncomeKeyAnnotation.class);
                        String value = annotation.value();
                        try {
                            String str = (String) declaredField.get(companyHkIncomeStatementDO);
                            if (StringUtils.isNotBlank(str)) {
                                SpiderResModel.DataModel dataModel = getDataModel(str);
                                List<SpiderResModel.DataModel> dataModels = dataListMap.get(value);
                                if (!CollectionUtils.isEmpty(dataModels)) {
                                    dataModels.add(dataModel);
                                } else {
                                    List<SpiderResModel.DataModel> dataModelList = new ArrayList<>();
                                    dataModelList.add(dataModel);
                                    dataListMap.put(value, dataModelList);
                                }
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return dateList;
    }

    //排序过滤日期
    private List<String> listDate(Integer period, List<String> list) {
        List<String> newList = null;
        list = list.stream().sorted(Comparator.comparing(s-> StringUtils.substringAfter(s, ")"), Comparator.reverseOrder())).collect(Collectors.toList());
        switch (period) {
            case 1:
                newList = list.stream().filter(s->StringUtils.containsAny(s,"Q1", "Q2", "Q3", "Q4")).collect(Collectors.toList());
                break;
            case 2:
                newList = list.stream().filter(s->StringUtils.containsAny(s, "Q6", "Q9")).collect(Collectors.toList());
                break;
            case 3:
                newList = list.stream().filter(s->StringUtils.containsAny(s,"FY")).collect(Collectors.toList());
                break;
            default:
                break;
        }
        return newList;
    }

    //美股处理
    private List<ListSingleIncomeStatementVO> getSingleUS(Integer incomeType, String symbol, Integer period) {
        List<ListSingleIncomeStatementVO> list = new ArrayList<>();
        List<CompanyUsIncomeStatementDO> companyUsIncomeStatementDOS = companyUsIncomeStatementMapper.listBySymbol(symbol);
        if (!CollectionUtils.isEmpty(companyUsIncomeStatementDOS)) {
            List<String> dateListAll = companyUsIncomeStatementDOS.stream().map(CompanyUsIncomeStatementDO::getQuarter).collect(Collectors.toList());
            List<String> dateList = listDate(period, dateListAll);
            Map<String, CompanyUsIncomeStatementDO> collect = companyUsIncomeStatementDOS.stream().collect(Collectors.toMap(s -> s.getQuarter(), s -> s));
            if (!CollectionUtils.isEmpty(dateList)) {
                if (dateList.size() > SIZE) {
                    dateList = dateList.subList(dateList.size()-SIZE, dateList.size());
                }
                for (String date : dateList) {
                    CompanyUsIncomeStatementDO companyUsIncomeStatementDO = collect.get(date);
                    String data = "";
                    if (companyUsIncomeStatementDO != null) {
                        switch (incomeType) {
                            case 1:
                                data = companyUsIncomeStatementDO.getTotalRevenue();
                                break;
                            case 2:
                                data = companyUsIncomeStatementDO.getGrossProfit();
                                break;
                            case 3:
                                data = companyUsIncomeStatementDO.getNetIncome();
                                break;
                            default:
                                break;
                        }
                    }
                    if (StringUtils.isNotBlank(data)) {
                        ListSingleIncomeStatementVO listSingleIncomeStatementVO = handleSingle(data, companyUsIncomeStatementDO.getQuarter());
                        listSingleIncomeStatementVO.setQuarter(date);
                        list.add(listSingleIncomeStatementVO);
                    }
                }
            }

        }
        return list;
    }

    //港股处理
    private List<ListSingleIncomeStatementVO> getSingleHK(Integer incomeType, String symbol, Integer period) {
        List<ListSingleIncomeStatementVO> list = new ArrayList<>();
        List<CompanyHkIncomeStatementDO> companyHkIncomeStatementDOS = companyHkIncomeStatementMapper.listBySymbol(symbol);
        if (!CollectionUtils.isEmpty(companyHkIncomeStatementDOS)) {
            List<String> dateListAll = companyHkIncomeStatementDOS.stream().map(CompanyHkIncomeStatementDO::getQuarter).collect(Collectors.toList());
            List<String> dateList = listDate(period, dateListAll);
            Map<String, CompanyHkIncomeStatementDO> collect = companyHkIncomeStatementDOS.stream().collect(Collectors.toMap(s -> s.getQuarter(), s -> s));
            if (!CollectionUtils.isEmpty(dateList)) {
                if (dateList.size() > SIZE) {
                    dateList = dateList.subList(dateList.size()-SIZE, dateList.size());
                }
                for (String date : dateList) {
                    CompanyHkIncomeStatementDO companyHkIncomeStatementDO = collect.get(date);
                    String data = "";
                    if (companyHkIncomeStatementDO != null) {
                        switch (incomeType) {
                            case 1:
                                data = companyHkIncomeStatementDO.getOperatingIncome();
                                break;
                            case 2:
                                data = companyHkIncomeStatementDO.getGrossProfit();
                                break;
                            case 3:
                                data = companyHkIncomeStatementDO.getEarningAfterTax();
                                break;
                            default:
                                break;
                        }
                    }
                    if (StringUtils.isNotBlank(data)) {
                        ListSingleIncomeStatementVO listSingleIncomeStatementVO = handleSingle(data, companyHkIncomeStatementDO.getQuarter());
                        listSingleIncomeStatementVO.setQuarter(date);
                        list.add(listSingleIncomeStatementVO);
                    }
                }
            }

        }
        return list;
    }

    //单个数据处理
    private ListSingleIncomeStatementVO handleSingle(String data, String quarter) {
        ListSingleIncomeStatementVO listSingleIncomeStatementVO = new ListSingleIncomeStatementVO();
        JSONObject jsonObject = JSON.parseObject(data);
        String ratio = jsonObject.getString("ratio");
        BigDecimal value = jsonObject.getBigDecimal("raw_value");
        listSingleIncomeStatementVO.setValue(value);
        listSingleIncomeStatementVO.setRatio(ratio);
        listSingleIncomeStatementVO.setQuarter(quarter);
        return listSingleIncomeStatementVO;
    }

    //单个数据处理
    private SpiderResModel.DataModel getDataModel(String data) {
        SpiderResModel.DataModel dataModel = new SpiderResModel.DataModel();
        JSONObject jsonObject = JSON.parseObject(data);
        String ratio = jsonObject.getString("ratio");
        BigDecimal value = jsonObject.getBigDecimal("raw_value");
        dataModel.setValue(value);
        dataModel.setRatio(ratio);
        return dataModel;
    }
}

