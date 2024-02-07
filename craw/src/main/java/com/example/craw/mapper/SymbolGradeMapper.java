package com.example.craw.mapper;

import com.example.craw.model.SymbolGradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SymbolGradeMapper {

    int deleteByType(@Param("type") Integer type);

    int insertBatch(List<SymbolGradeDO> list);

    SymbolGradeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SymbolGradeDO record);

    int updateByPrimaryKey(SymbolGradeDO record);
}