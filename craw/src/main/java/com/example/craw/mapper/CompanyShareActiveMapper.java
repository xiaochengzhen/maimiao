package com.example.craw.mapper;

import com.example.craw.model.CompanyShareActiveDO;
import com.example.craw.dto.vo.CompanyShareActiveTotalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyShareActiveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyShareActiveDO record);

    int insertSelective(CompanyShareActiveDO record);

    CompanyShareActiveDO selectByPrimaryKey(Long id);

    CompanyShareActiveDO getBySymbolAndName(@Param("symbol") String symbol, @Param("name") String name);

    int updateByPrimaryKeySelective(CompanyShareActiveDO record);

    List<CompanyShareActiveDO> listBySymbol(@Param("type") Integer type, @Param("symbol") String symbol);

    CompanyShareActiveTotalVO statBySymbol(@Param("type") Integer type, @Param("symbol") String symbol);

    int updateByPrimaryKey(CompanyShareActiveDO record);
}