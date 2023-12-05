package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.query.ListCompanyIncomeStatementQuery;
import com.example.craw.dto.vo.ListSingleIncomeStatementVO;
import com.example.craw.mapper.CompanyHkIncomeStatementMapper;
import com.example.craw.mapper.CompanyUsIncomeStatementMapper;
import com.example.craw.model.CompanyHkIncomeStatementDO;
import com.example.craw.model.CompanyUsIncomeStatementDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
     * @description 查询单个财务指标列表
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
            extractedUS(incomeType, symbol, list);
        } else {
            extractedHK(incomeType, symbol, list);
        }
        if (!CollectionUtils.isEmpty(list)) {
            list = list.stream().sorted(Comparator.comparing(s-> StringUtils.substringAfter(s.getQuarter(), ")"))).collect(Collectors.toList());
            list = filterQuarter(period, list);
            if (list.size() > SIZE) {
                list = list.subList(list.size()-SIZE, list.size());
            }
            list.forEach(s->s.setQuarter(StringUtils.substringBetween(s.getQuarter(), ")", "/") + "/"+StringUtils.substringBetween(s.getQuarter(), "(", ")") ));
        }
        return list;
    }

    //季度筛选
    private List<ListSingleIncomeStatementVO> filterQuarter(Integer period, List<ListSingleIncomeStatementVO> list) {
        List<ListSingleIncomeStatementVO> newList = null;
        switch (period) {
            case 1:
                newList = list.stream().filter(s->StringUtils.containsAny(s.getQuarter(),"Q1", "Q2", "Q3", "Q4")).collect(Collectors.toList());
                break;
            case 2:
                newList = list.stream().filter(s->StringUtils.containsAny(s.getQuarter(), "Q6", "Q9")).collect(Collectors.toList());
                break;
            case 3:
                newList = list.stream().filter(s->StringUtils.containsAny(s.getQuarter(),"FY")).collect(Collectors.toList());
                break;
            default:
                break;
        }
        return newList;
    }

    //美股处理
    private void extractedUS(Integer incomeType, String symbol, List<ListSingleIncomeStatementVO> list) {
        List<CompanyUsIncomeStatementDO> companyUsIncomeStatementDOS = companyUsIncomeStatementMapper.listBySymbol(symbol);
        if (!CollectionUtils.isEmpty(companyUsIncomeStatementDOS)) {
            for (CompanyUsIncomeStatementDO companyUsIncomeStatementDO : companyUsIncomeStatementDOS) {
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
                    ListSingleIncomeStatementVO listSingleIncomeStatementVO = new ListSingleIncomeStatementVO();
                    JSONObject jsonObject = JSON.parseObject(data);
                    String ratio = jsonObject.getString("ratio");
                    String value = jsonObject.getString("raw_value");
                    String quarter = companyUsIncomeStatementDO.getQuarter();
                    listSingleIncomeStatementVO.setValue(value);
                    listSingleIncomeStatementVO.setRatio(ratio);
                    listSingleIncomeStatementVO.setQuarter(quarter);
                    list.add(listSingleIncomeStatementVO);
                }
            }
        }
    }

    //港股处理
    private void extractedHK(Integer incomeType, String symbol, List<ListSingleIncomeStatementVO> list) {
        List<CompanyHkIncomeStatementDO> companyHkIncomeStatementDOS = companyHkIncomeStatementMapper.listBySymbol(symbol);
        if (!CollectionUtils.isEmpty(companyHkIncomeStatementDOS)) {
            for (CompanyHkIncomeStatementDO companyHkIncomeStatementDO : companyHkIncomeStatementDOS) {
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
                    ListSingleIncomeStatementVO listSingleIncomeStatementVO = new ListSingleIncomeStatementVO();
                    JSONObject jsonObject = JSON.parseObject(data);
                    String ratio = jsonObject.getString("ratio");
                    String value = jsonObject.getString("raw_value");
                    String quarter = companyHkIncomeStatementDO.getQuarter();
                    listSingleIncomeStatementVO.setValue(value);
                    listSingleIncomeStatementVO.setRatio(ratio);
                    listSingleIncomeStatementVO.setQuarter(quarter);
                    list.add(listSingleIncomeStatementVO);
                }
            }
        }
    }

}

