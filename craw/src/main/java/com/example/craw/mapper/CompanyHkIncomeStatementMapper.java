package com.example.craw.mapper;

import com.example.craw.model.CompanyHkIncomeStatementDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 利润表的mapper（hk）
 * @author xiaobo
 * @date `2023/12/4` 8:16
 */
@Mapper
public interface CompanyHkIncomeStatementMapper {

    int insert(CompanyHkIncomeStatementDO record);

    CompanyHkIncomeStatementDO selectByPrimaryKey(@Param("symbol") String symbol, @Param("quarter") String quarter, @Param("period") Integer period);

    List<CompanyHkIncomeStatementDO> listBySymbol(@Param("symbol") String symbol);

    int updateByPrimaryKeySelective(CompanyHkIncomeStatementDO record);

}