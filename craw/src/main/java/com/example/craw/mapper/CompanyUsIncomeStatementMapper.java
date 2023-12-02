package com.example.craw.mapper;

import com.example.craw.model.CompanyUsIncomeStatementDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyUsIncomeStatementMapper {

    int insert(CompanyUsIncomeStatementDO record);

    CompanyUsIncomeStatementDO selectByPrimaryKey(@Param("symbol") String symbol, @Param("quarter") String quarter);

    int updateByPrimaryKeySelective(CompanyUsIncomeStatementDO record);

}