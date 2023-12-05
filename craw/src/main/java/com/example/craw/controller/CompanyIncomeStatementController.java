package com.example.craw.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.SpiderResModel;
import com.example.craw.dto.query.ListCompanyFinancialIndicatorQuery;
import com.example.craw.dto.query.ListCompanyIncomeStatementQuery;
import com.example.craw.dto.vo.CompanyFinancialIndicatorVO;
import com.example.craw.dto.vo.ListSingleIncomeStatementVO;
import com.example.craw.dto.vo.ListSingleIndicatorVO;
import com.example.craw.service.CompanyFinancialIndicatorService;
import com.example.craw.service.CompanyIncomeStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description 利润表的controller
 * @author xiaobo
 * @date 2023/12/4 10:04
 */
@RestController
@RequestMapping("/companyIncomeStatement")
public class CompanyIncomeStatementController {
    @Autowired
    private CompanyIncomeStatementService companyIncomeStatementService;

    /**
     * @description 查询营业收入、营业利润、净利润列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    @GetMapping("/listSingleIncomeStatement")
    public Object listSingleIncomeStatement(ListCompanyIncomeStatementQuery listCompanyIncomeStatementQuery) {
        List<ListSingleIncomeStatementVO> listSingleIncomeStatementVOS = companyIncomeStatementService.listSingleIncomeStatement(listCompanyIncomeStatementQuery);
        return listSingleIncomeStatementVOS;
    }

    /**
     * @description 查询利润表列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    @GetMapping("/list")
    public Object list(ListCompanyIncomeStatementQuery listCompanyIncomeStatementQuery) {
        SpiderResModel spiderResModel = companyIncomeStatementService.list(listCompanyIncomeStatementQuery, "en_US");
        return spiderResModel;
    }
}
