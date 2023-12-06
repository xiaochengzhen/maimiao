package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.query.ListCompanyFinancialIndicatorQuery;
import com.example.craw.dto.vo.CompanyFinancialIndicatorVO;
import com.example.craw.dto.vo.ListSingleIndicatorVO;
import com.example.craw.mapper.CompanyFinancialIndicatorMapper;
import com.example.craw.model.CompanyFinancialIndicatorDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
     * @description 查询单个财务指标列表
     * @author xiaobo
     * @date 2023/12/4 14:22
     */
    public List<ListSingleIndicatorVO> listSingleIndicator(ListCompanyFinancialIndicatorQuery listCompanyFinancialIndicatorQuery) {
        Integer indicatorType = listCompanyFinancialIndicatorQuery.getIndicatorType();
        List<ListSingleIndicatorVO> list = new ArrayList<>();
        CompanyFinancialIndicatorDO companyFinancialIndicatorDO = companyFinancialIndicatorMapper.selectByPrimaryKey(listCompanyFinancialIndicatorQuery.getSymbol(), listCompanyFinancialIndicatorQuery.getPeriod());
        String data = "";
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
            JSONObject jsonObject = JSON.parseObject(data);
            String dataInfo = jsonObject.getString("dataInfo");
            if (StringUtils.isNotBlank(dataInfo)) {
                List<ListSingleIndicatorVO> listCompanyFinancialIndicatorVOS = JSON.parseArray(dataInfo, ListSingleIndicatorVO.class);
                if (!CollectionUtils.isEmpty(listCompanyFinancialIndicatorVOS)) {
                    if (listCompanyFinancialIndicatorVOS.size() > SIZE) {
                        listCompanyFinancialIndicatorVOS = listCompanyFinancialIndicatorVOS.subList(listCompanyFinancialIndicatorVOS.size()-SIZE, listCompanyFinancialIndicatorVOS.size());
                    }
                    for (ListSingleIndicatorVO listCompanyFinancialIndicatorVO : listCompanyFinancialIndicatorVOS) {
                        String year = listCompanyFinancialIndicatorVO.getYear();
                        Integer financialType = listCompanyFinancialIndicatorVO.getFinancialType();
                        listCompanyFinancialIndicatorVO.setQuarter(getQuarter(year, financialType));
                        list.add(listCompanyFinancialIndicatorVO);
                    }
                }
            }
        }
        return list;
    }


    /**
     * @description 查询财务指标列表
     * @author xiaobo
     * @date 2023/12/4 14:22
     */
    public CompanyFinancialIndicatorVO listCompanyFinancialIndicator(ListCompanyFinancialIndicatorQuery listCompanyFinancialIndicatorQuery) {
        CompanyFinancialIndicatorVO companyFinancialIndicatorVO = new CompanyFinancialIndicatorVO();
        CompanyFinancialIndicatorDO companyFinancialIndicatorDO = companyFinancialIndicatorMapper.selectByPrimaryKey(listCompanyFinancialIndicatorQuery.getSymbol(), listCompanyFinancialIndicatorQuery.getPeriod());
        if (companyFinancialIndicatorDO != null) {
            String eps = companyFinancialIndicatorDO.getEps();
            if (StringUtils.isNotBlank(eps)) {
                companyFinancialIndicatorVO.setEps(getSingleIndicatorList(eps));
            }
            String bvps = companyFinancialIndicatorDO.getBvps();
            if (StringUtils.isNotBlank(bvps)) {
                companyFinancialIndicatorVO.setBvps(getSingleIndicatorList(bvps));
            }
            String currentRatio = companyFinancialIndicatorDO.getCurrentRatio();
            if (StringUtils.isNotBlank(currentRatio)) {
                companyFinancialIndicatorVO.setCurrentRatio(getSingleIndicatorList(currentRatio));
            }
            String quickRatio = companyFinancialIndicatorDO.getQuickRatio();
            if (StringUtils.isNotBlank(quickRatio)) {
                companyFinancialIndicatorVO.setQuickRatio(getSingleIndicatorList(quickRatio));
            }
            String roe = companyFinancialIndicatorDO.getRoe();
            if (StringUtils.isNotBlank(roe)) {
                companyFinancialIndicatorVO.setRoe(getSingleIndicatorList(roe));
            }
            String roa = companyFinancialIndicatorDO.getRoa();
            if (StringUtils.isNotBlank(roa)) {
                companyFinancialIndicatorVO.setRoa(getSingleIndicatorList(roa));
            }
            String grossMargin = companyFinancialIndicatorDO.getGrossMargin();
            if (StringUtils.isNotBlank(grossMargin)) {
                companyFinancialIndicatorVO.setGrossMargin(getSingleIndicatorList(grossMargin));
            }
            String netMargin = companyFinancialIndicatorDO.getNetMargin();
            if (StringUtils.isNotBlank(netMargin)) {
                companyFinancialIndicatorVO.setNetMargin(getSingleIndicatorList(netMargin));
            }
            String fcf = companyFinancialIndicatorDO.getFcf();
            if (StringUtils.isNotBlank(fcf)) {
                companyFinancialIndicatorVO.setFcf(getSingleIndicatorList(fcf));
            }
        }
        return companyFinancialIndicatorVO;
    }

    //获得单个指标的list
    private List<ListSingleIndicatorVO> getSingleIndicatorList(String eps) {
        List<ListSingleIndicatorVO> listSingleIndicatorVOS = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(eps);
        String dataInfo = jsonObject.getString("dataInfo");
        if (StringUtils.isNotBlank(dataInfo)) {
            List<ListSingleIndicatorVO> listCompanyFinancialIndicatorVOS = JSON.parseArray(dataInfo, ListSingleIndicatorVO.class);
            if (!CollectionUtils.isEmpty(listCompanyFinancialIndicatorVOS)) {
                for (ListSingleIndicatorVO listCompanyFinancialIndicatorVO : listCompanyFinancialIndicatorVOS) {
                    String year = listCompanyFinancialIndicatorVO.getYear();
                    Integer financialType = listCompanyFinancialIndicatorVO.getFinancialType();
                    listCompanyFinancialIndicatorVO.setQuarter(getQuarter(year, financialType));
                    listSingleIndicatorVOS.add(listCompanyFinancialIndicatorVO);
                }
            }
        }
        return listSingleIndicatorVOS;
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
