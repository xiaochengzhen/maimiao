package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.SpiderResModel;
import com.example.craw.dto.query.ListCompanyFinancialIndicatorQuery;
import com.example.craw.dto.query.ListCompanyIncomeStatementQuery;
import com.example.craw.dto.vo.CompanyFinancialIndicatorVO;
import com.example.craw.dto.vo.ListSingleIncomeStatementVO;
import com.example.craw.dto.vo.ListSingleIndicatorVO;
import com.example.craw.http.IncomeKeyAnnotation;
import com.example.craw.mapper.CompanyFinancialIndicatorMapper;
import com.example.craw.mapper.CompanyHkIncomeStatementMapper;
import com.example.craw.mapper.CompanyUsIncomeStatementMapper;
import com.example.craw.model.CompanyFinancialIndicatorDO;
import com.example.craw.model.CompanyHkIncomeStatementDO;
import com.example.craw.model.CompanyUsIncomeStatementDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.craw.util.CrawUtil.*;

/**
 * @description 财务指标的service
 * @author xiaobo
 * @date 2023/12/4 13:50
 */
@Service
public class CompanyFinancialIndicatorService {
    private static final int SIZE = 5;

    @Autowired
    private CompanyFinancialIndicatorMapper companyFinancialIndicatorMapper;

    /**
     * @description 查询单个利润表指标
     * @author xiaobo
     * @date 2023/12/4 14:22
     */
    public List<ListSingleIncomeStatementVO> listSingleIndicator(ListCompanyFinancialIndicatorQuery listCompanyFinancialIndicatorQuery) {
        Integer indicatorType = listCompanyFinancialIndicatorQuery.getIndicatorType();
        Integer period = listCompanyFinancialIndicatorQuery.getPeriod();
        String symbol = listCompanyFinancialIndicatorQuery.getSymbol();
        String market = StringUtils.substringAfter(symbol, ".");
        List<ListSingleIncomeStatementVO> list = new ArrayList<>();
        List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOS = companyFinancialIndicatorMapper.listBySymbolAndPeriod(symbol, period);
        if (!CollectionUtils.isEmpty(companyFinancialIndicatorDOS)) {
            List<String> dateList = companyFinancialIndicatorDOS.stream().map(CompanyFinancialIndicatorDO::getQuarter).collect(Collectors.toList());
            Map<String, CompanyFinancialIndicatorDO> collect = companyFinancialIndicatorDOS.stream().collect(Collectors.toMap(s -> s.getQuarter(), s -> s));
            if (!CollectionUtils.isEmpty(dateList)) {
                if (dateList.size() > SIZE) {
                    dateList = dateList.subList(dateList.size()-SIZE, dateList.size());
                }
                for (String date : dateList) {
                    String data = "";
                    CompanyFinancialIndicatorDO companyFinancialIndicatorDO = collect.get(date);
                    if (companyFinancialIndicatorDO != null) {
                        switch (indicatorType) {
                            case 1:
                                data = companyFinancialIndicatorDO.getEps();
                                break;
                            case 2:
                                data = companyFinancialIndicatorDO.getBvps();
                                break;
                            case 3:
                                data = companyFinancialIndicatorDO.getCurrentRatio();
                                break;
                            case 4:
                                data = companyFinancialIndicatorDO.getQuickRatio();
                                break;
                            case 5:
                                data = companyFinancialIndicatorDO.getRoe();
                                break;
                            case 6:
                                data = companyFinancialIndicatorDO.getRoa();
                                break;
                            case 7:
                                data = companyFinancialIndicatorDO.getGrossMargin();
                                break;
                            case 8:
                                data = companyFinancialIndicatorDO.getNetMargin();
                                break;
                            case 9:
                                data = companyFinancialIndicatorDO.getFcf();
                                break;
                            default:
                                break;
                        }
                    }
                    if (StringUtils.isNotBlank(data)) {
                        ListSingleIncomeStatementVO listSingleIncomeStatementVO = handleSingle(data, date);
                        listSingleIncomeStatementVO.setQuarter(date);
                        list.add(listSingleIncomeStatementVO);
                    }
                }
            }
        }
        return list;
    }


    /**
     * @description 查询利润表详情
     * @author xiaobo
     * @date 2023/12/4 14:22
     */
    public SpiderResModel list(ListCompanyFinancialIndicatorQuery listCompanyFinancialIndicatorQuery, String language) {
        SpiderResModel spiderResModel = new SpiderResModel();
        Integer period = listCompanyFinancialIndicatorQuery.getPeriod();
        String symbol = listCompanyFinancialIndicatorQuery.getSymbol();
        String market = StringUtils.substringAfter(symbol, ".");
        //key==dataList
        Map<String, List<SpiderResModel.DataModel>> dataListMap = new HashMap<>();
        //key==title
        Map<String, String> titleMap = getTitleMap(language, market, CompanyFinancialIndicatorDO.class);
        //date list
        List<String> dateList = new ArrayList<>();
        List<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOS = companyFinancialIndicatorMapper.listBySymbolAndPeriod(symbol, period);
        if (!CollectionUtils.isEmpty(companyFinancialIndicatorDOS)) {
            dateList = companyFinancialIndicatorDOS.stream().map(CompanyFinancialIndicatorDO::getQuarter).collect(Collectors.toList());
            Map<String, CompanyFinancialIndicatorDO> dateDataMap = companyFinancialIndicatorDOS.stream().collect(Collectors.toMap(s -> s.getQuarter(), s -> s));
            for (String date : dateList) {
                CompanyFinancialIndicatorDO companyFinancialIndicatorDO = dateDataMap.get(date);
                Class<CompanyFinancialIndicatorDO> companyFinancialIndicatorDOClass = CompanyFinancialIndicatorDO.class;
                Field[] declaredFields = companyFinancialIndicatorDOClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    if (declaredField.isAnnotationPresent(IncomeKeyAnnotation.class)) {
                        IncomeKeyAnnotation annotation = declaredField.getAnnotation(IncomeKeyAnnotation.class);
                        String value = annotation.value();
                        try {
                            String str = declaredField.get(companyFinancialIndicatorDO) == null?"":(String) declaredField.get(companyFinancialIndicatorDO);
                            SpiderResModel.DataModel dataModel = getDataModel(str);
                            List<SpiderResModel.DataModel> dataModels = dataListMap.get(value);
                            if (!CollectionUtils.isEmpty(dataModels)) {
                                dataModels.add(dataModel);
                            } else {
                                List<SpiderResModel.DataModel> dataModelList = new ArrayList<>();
                                dataModelList.add(dataModel);
                                dataListMap.put(value, dataModelList);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
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

}
