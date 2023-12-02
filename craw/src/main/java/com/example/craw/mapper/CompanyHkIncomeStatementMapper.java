package com.example.craw.mapper;

import com.example.craw.model.CompanyHkIncomeStatementDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyHkIncomeStatementMapper {

    int insert(CompanyHkIncomeStatementDO record);

    CompanyHkIncomeStatementDO selectByPrimaryKey(@Param("symbol") String symbol, @Param("quarter") String quarter);

    int updateByPrimaryKeySelective(CompanyHkIncomeStatementDO record);

}