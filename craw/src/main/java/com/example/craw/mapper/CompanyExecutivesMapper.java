package com.example.craw.mapper;

import com.example.craw.model.CompanyExecutivesDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyExecutivesMapper {

    int insert(CompanyExecutivesDO record);

    int insertSelective(CompanyExecutivesDO record);

    CompanyExecutivesDO selectByPrimaryKey(Long id);

    List<CompanyExecutivesDO> listBySymbol(@Param("symbol") String symbol);

    int updateByPrimaryKeySelective(CompanyExecutivesDO record);

    int updateByPrimaryKey(CompanyExecutivesDO record);


}