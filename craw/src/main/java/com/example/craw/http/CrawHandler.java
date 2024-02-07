package com.example.craw.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.craw.dto.RequestDTO;
import com.example.craw.mapper.CompanyFinancialRealMapper;
import com.example.craw.model.CompanyFinancialRealDO;
import com.example.craw.model.CompanyHkIncomeStatementDO;
import com.example.craw.model.CompanyUsIncomeStatementDO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description handler抽象类
 * @author xiaobo
 * @date 2023/11/29 8:50
 */
@Data
public abstract class CrawHandler {
    @Autowired
    private CompanyFinancialRealMapper companyFinancialRealMapper;

    //匹配相应的handler
    public abstract boolean match(CrawEnum crawEnum, String market);

    //执行数据爬取
    public void craw(RequestDTO requestDTO) {
        httpRequest(requestDTO);
        convertResponse(requestDTO);
        saveData(requestDTO);
        System.out.println();
    };

   // @Transactional
    public void testtra(){};

    //http 请求数据
    abstract void httpRequest(RequestDTO requestDTO);

    //相应数据转换
    abstract void convertResponse(RequestDTO requestDTO);

    //转换好的数据存库
    abstract void saveData(RequestDTO requestDTO);


    //保存财务指标最新数据
    public void saveTotalIncome(String lastQ, CompanyHkIncomeStatementDO companyHkIncomeStatementDO, CompanyUsIncomeStatementDO companyUsIncomeStatementDO) {
        if (StringUtils.isBlank(lastQ)) {
            return;
        }
        String symbol = "";
        BigDecimal revenue = null;
        String quarter = "";
        String incomeRevenue = "";
        if (companyHkIncomeStatementDO != null) {
            symbol = companyHkIncomeStatementDO.getSymbol();
            incomeRevenue = companyHkIncomeStatementDO.getOperatingIncome();
            quarter = companyHkIncomeStatementDO.getQuarter();
        }
        if (companyUsIncomeStatementDO != null) {
            symbol = companyUsIncomeStatementDO.getSymbol();
            incomeRevenue = companyUsIncomeStatementDO.getTotalRevenue();
            quarter = companyUsIncomeStatementDO.getQuarter();
        }
        if (StringUtils.isNotBlank(incomeRevenue)) {
            JSONObject jsonObject = JSON.parseObject(incomeRevenue);
            revenue = jsonObject.getBigDecimal("raw_value");
        }
        if (StringUtils.isNotBlank(symbol) && StringUtils.isNotBlank(quarter) && lastQ.equals(quarter) && revenue != null) {
            CompanyFinancialRealDO companyFinancialRealRaw = companyFinancialRealMapper.selectByPrimaryKey(symbol);
            CompanyFinancialRealDO companyFinancialRealDO = new CompanyFinancialRealDO();
            companyFinancialRealDO.setRevenue(revenue);
            companyFinancialRealDO.setSymbol(symbol);
            //如果记录存在，数据是否为空都修改
            if (companyFinancialRealRaw != null) {
                companyFinancialRealDO.setId(companyFinancialRealRaw.getId());
                companyFinancialRealMapper.update(companyFinancialRealDO);
            }
            //记录不存在，插入
            if (companyFinancialRealRaw == null) {
                companyFinancialRealMapper.insert(companyFinancialRealDO);
            }
        }
    }

    public String getLastQ(List<String> title) {
        if (!CollectionUtils.isEmpty(title)) {
            for (String s : title) {
                if (StringUtils.startsWith(s, "(Q")) {
                    return s;
                }
            }
        }
        return "";
    }
}
