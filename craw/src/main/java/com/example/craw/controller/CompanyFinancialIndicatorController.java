package com.example.craw.controller;

import com.example.craw.dto.query.ListCompanyFinancialIndicatorQuery;
import com.example.craw.dto.vo.CompanyFinancialIndicatorVO;
import com.example.craw.dto.vo.ListSingleIndicatorVO;
import com.example.craw.service.CompanyFinancialIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 财务指标的controller
 * @author xiaobo
 * @date 2023/12/4 10:04
 */
@RestController
@RequestMapping("/companyFinancialIndicator")
public class CompanyFinancialIndicatorController {
    @Autowired
    private CompanyFinancialIndicatorService companyFinancialIndicatorService;

    /**
     * @description 查询单个财务指标列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    @GetMapping("/listSingleIndicator")
    public Object listSingleIndicator(ListCompanyFinancialIndicatorQuery listCompanyFinancialIndicatorQuery) {
        List<ListSingleIndicatorVO> listSingleIndicator = companyFinancialIndicatorService.listSingleIndicator(listCompanyFinancialIndicatorQuery);
        return listSingleIndicator;
    }

    /**
     * @description 查询财务指标列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    @GetMapping("/list")
    public Object list(ListCompanyFinancialIndicatorQuery listCompanyFinancialIndicatorQuery) {
        CompanyFinancialIndicatorVO companyFinancialIndicatorVO = companyFinancialIndicatorService.listCompanyFinancialIndicator(listCompanyFinancialIndicatorQuery);
        return companyFinancialIndicatorVO;
    }
}
