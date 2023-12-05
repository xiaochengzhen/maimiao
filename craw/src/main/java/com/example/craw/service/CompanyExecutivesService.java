package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.CompanyExecutivesDO;
import com.example.craw.CompanyShareActiveDO;
import com.example.craw.controller.CompanyExecutivesController;
import com.example.craw.dto.vo.CompanyExecutivesVO;
import com.example.craw.mapper.CompanyExecutivesMapper;
import com.example.craw.mapper.CompanyShareActiveMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 公司高管的service
 * @author xiaobo
 * @date 2023/12/5 10:02
 */
@Service
public class CompanyExecutivesService {

    @Autowired
    private CompanyExecutivesMapper companyExecutivesMapper;
    @Autowired
    private CompanyShareActiveMapper companyShareActiveMapper;

    /**
     * @description 查询股东列表
     * @author xiaobo
     * @param symbol, language
     * @return java.util.List<com.example.craw.dto.vo.CompanyExecutivesVO>
     * @date 2023/12/5 10:19
     */
    public List<CompanyExecutivesVO> listCompanyExecutives(String symbol, String language) {
        List<CompanyExecutivesVO> list = new ArrayList<>();
        List<CompanyExecutivesDO> companyExecutivesDOS = companyExecutivesMapper.listBySymbol(symbol);
        if (!CollectionUtils.isEmpty(companyExecutivesDOS)) {
            for (CompanyExecutivesDO companyExecutivesDO : companyExecutivesDOS) {
                CompanyExecutivesVO companyExecutivesVO = new CompanyExecutivesVO();
                companyExecutivesVO.setName(getName(companyExecutivesDO.getName(), language));
                CompanyShareActiveDO bySymbolAndName = companyShareActiveMapper.getBySymbolAndName(symbol, getName(companyExecutivesDO.getName(), "en_US"));
                if (bySymbolAndName != null) {
                    companyExecutivesVO.setShareRatio(bySymbolAndName.getShareRatio());
                }
                list.add(companyExecutivesVO);
            }
        }
        return list;
    }

    private String getName(String name, String language) {
        if (StringUtils.isNotBlank(name)) {
            JSONObject jsonObject = JSON.parseObject(name);
            String string = jsonObject.getString(language);
            return string;
        }
        return "";
    }
}
