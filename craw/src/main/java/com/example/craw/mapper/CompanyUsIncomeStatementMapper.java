package com.example.craw.mapper;

import com.example.craw.model.CompanyUsIncomeStatementDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 利润表的mapper（us）
 * @author xiaobo
 * @date `2023/12/4` 8:16
 */
@Mapper
public interface CompanyUsIncomeStatementMapper {

    int insert(CompanyUsIncomeStatementDO record);

    CompanyUsIncomeStatementDO selectByPrimaryKey(@Param("symbol") String symbol, @Param("quarter") String quarter);

    List<CompanyUsIncomeStatementDO> listBySymbol(@Param("symbol") String symbol);

    int updateByPrimaryKeySelective(CompanyUsIncomeStatementDO record);

}