package com.example.craw.controller;

import com.example.craw.dto.vo.CompanyShareActiveTotalVO;
import com.example.craw.dto.vo.CompanyShareActiveVO;
import com.example.craw.service.CompanyShareActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 持股变动的controler
 * @author xiaobo
 * @date 2023/12/5 10:04
 */
@RestController
@RequestMapping("/companyShareActive")
public class CompanyShareActiveController {

    @Autowired
    private CompanyShareActiveService companyShareActiveService;

    /**
     * @description 查询持股变动列表
     * @author xiaobo
     * @date 2023/12/5 10:53
     */
    @GetMapping("/list")
    public Object list(Integer type, String symbol) {
        //TODO  page
        List<CompanyShareActiveVO> list = companyShareActiveService.listCompanyShareActive(type, symbol, "zh_CN");
        return list;
    }

    /**
     * @description 机构统计
     * @author xiaobo
     * @date 2023/12/5 10:54
     */
    @GetMapping("/stat")
    public Object stat(String symbol) {
        CompanyShareActiveTotalVO companyShareActiveTotalVO = companyShareActiveService.statCompanyShareActive(symbol);
        return companyShareActiveTotalVO;
    }

}
