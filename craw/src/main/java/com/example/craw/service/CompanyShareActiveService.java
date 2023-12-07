package com.example.craw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.model.CompanyShareActiveDO;
import com.example.craw.dto.vo.CompanyShareActiveTotalVO;
import com.example.craw.dto.vo.CompanyShareActiveVO;
import com.example.craw.mapper.CompanyShareActiveMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 持股变动的service
 * @author xiaobo
 * @date 2023/12/5 10:02
 */
@Service
public class CompanyShareActiveService {

    @Autowired
    private CompanyShareActiveMapper companyShareActiveMapper;

    /**
     * @description 查询持股变动
     * @author xiaobo
     * @param symbol, language
     * @return java.util.List<com.example.craw.dto.vo.CompanyShareActiveVO>
     * @date 2023/12/5 10:27
     */
    public List<CompanyShareActiveVO> listCompanyShareActive(Integer type, String symbol, String language) {
        List<CompanyShareActiveVO> list = new ArrayList<>();
        List<CompanyShareActiveDO> companyShareActiveDOS = companyShareActiveMapper.listBySymbol(type, symbol);
        if (!CollectionUtils.isEmpty(companyShareActiveDOS)) {
            for (CompanyShareActiveDO companyShareActiveDO : companyShareActiveDOS) {
                CompanyShareActiveVO companyShareActiveVO = new CompanyShareActiveVO();
                companyShareActiveVO.setDate(companyShareActiveDO.getDate());
                companyShareActiveVO.setName(getName(companyShareActiveDO.getName(), language));
                companyShareActiveVO.setQuantity(companyShareActiveDO.getQuantity());
                companyShareActiveVO.setAmount(companyShareActiveDO.getAmount());
                companyShareActiveVO.setShareValue(companyShareActiveDO.getShareValue());
                companyShareActiveVO.setShareRatio(companyShareActiveDO.getShareRatio());
                list.add(companyShareActiveVO);
            }
        }
        return list;
    }

    /**
     * @description 机构统计
     * @author xiaobo
     * @param symbol
     * @return com.example.craw.dto.vo.CompanyShareActiveTotalVO
     * @date 2023/12/5 10:50
     */
    public CompanyShareActiveTotalVO statCompanyShareActive(String symbol) {
        CompanyShareActiveTotalVO companyShareActiveTotalVO = companyShareActiveMapper.statBySymbol(1, symbol);
        return companyShareActiveTotalVO;
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
