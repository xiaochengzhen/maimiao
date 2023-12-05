package com.example.craw.controller;

import com.example.craw.dto.vo.CompanyExecutivesVO;
import com.example.craw.mapper.CompanyExecutivesMapper;
import com.example.craw.service.CompanyExecutivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 公司高管的controller
 * @author xiaobo
 * @date 2023/12/5 10:04
 */
@RestController
@RequestMapping("/companyExecutives")
public class CompanyExecutivesController {

    @Autowired
    private CompanyExecutivesService companyExecutivesService;

    @GetMapping("/list")
    public Object list(String symbol) {
        List<CompanyExecutivesVO> list = companyExecutivesService.listCompanyExecutives(symbol, "zh_CN");
        return list;
    }
}
