package com.example.craw.mapper;

import com.example.craw.model.CompanyFinancialRealDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyFinancialRealMapper {

    int insert(CompanyFinancialRealDO record);

    CompanyFinancialRealDO selectByPrimaryKey(@Param("symbol") String symbol);

    int update(CompanyFinancialRealDO record);
}