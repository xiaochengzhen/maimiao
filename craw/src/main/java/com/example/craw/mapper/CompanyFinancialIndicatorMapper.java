package com.example.craw.mapper;

import com.example.craw.model.CompanyFinancialIndicatorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 财务指标的mapper
 * @author xiaobo
 * @date `2023/12/4` 8:16
 */
@Mapper
public interface CompanyFinancialIndicatorMapper {

    int insert(CompanyFinancialIndicatorDO record);

    CompanyFinancialIndicatorDO selectByPrimaryKey(@Param("symbol") String symbol, @Param("period") Integer period, @Param("quarter") String quarter);

    List<CompanyFinancialIndicatorDO> listBySymbolAndPeriod(@Param("symbol") String symbol, @Param("period") Integer period);

    int updateByPrimaryKeySelective(CompanyFinancialIndicatorDO record);

}