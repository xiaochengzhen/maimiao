package com.example.craw.controller;

import com.example.craw.dto.query.ListMainCompositionQuery;
import com.example.craw.dto.vo.ListMainCompositionDateVO;
import com.example.craw.dto.vo.ListMainCompositionVO;
import com.example.craw.service.CompanyMainCompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 主营构成的controller
 * @author xiaobo
 * @date 2023/12/4 10:04
 */
@RestController
@RequestMapping("/companyMainComposition")
public class CompanyMainCompositionController {
    @Autowired
    private CompanyMainCompositionService companyMainCompositionService;

    /**
     * @description 获取主营构成时间列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    @GetMapping("/listDate")
    public Object listDate(ListMainCompositionQuery listMainCompositionQuery) {
        List<ListMainCompositionDateVO> list = companyMainCompositionService.listMainCompositionDate(listMainCompositionQuery, "en_US");
        return list;
    }

    /**
     * @description 获取主营构成信息列表
     * @author xiaobo
     * @date 2023/12/4 10:32
     */
    @GetMapping("/list")
    public Object list(ListMainCompositionQuery listMainCompositionQuery) {
        List<ListMainCompositionVO> list = companyMainCompositionService.listMainComposition(listMainCompositionQuery, "zh_CN");
        return list;
    }
}
