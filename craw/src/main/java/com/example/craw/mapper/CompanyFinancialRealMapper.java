package com.example.craw.mapper;

import com.example.craw.model.CompanyFinancialRealDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description 财务指标汇总的mapper
 * @author xiaobo
 * @date `2023/12/4` 8:16
 */
@Mapper
public interface CompanyFinancialRealMapper {

    int insert(CompanyFinancialRealDO record);

    CompanyFinancialRealDO selectByPrimaryKey(@Param("symbol") String symbol);

    int update(CompanyFinancialRealDO record);
}