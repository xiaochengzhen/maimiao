package com.example.craw.mapper;

import com.example.craw.model.CompanyFinancialIndicatorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyFinancialIndicatorMapper {

    int insert(CompanyFinancialIndicatorDO record);

    CompanyFinancialIndicatorDO selectByPrimaryKey(@Param("symbol") String symbol, @Param("period") Integer period);

    int updateByPrimaryKeySelective(CompanyFinancialIndicatorDO record);

}