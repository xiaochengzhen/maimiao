package com.example.craw.mapper;

import com.example.craw.model.CompanyMainCompositionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMainCompositionMapper {

    int insert(CompanyMainCompositionDO record);

    int insertBatch(List<CompanyMainCompositionDO> list);

    int update(CompanyMainCompositionDO record);

    CompanyMainCompositionDO selectByQuarter(@Param("symbol") String symbol, @Param("quarter") String quarter);

}