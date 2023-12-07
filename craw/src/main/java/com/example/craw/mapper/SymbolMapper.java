package com.example.craw.mapper;

import com.example.craw.model.SymbolDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SymbolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SymbolDO record);

    int insertSelective(SymbolDO record);

    List<SymbolDO> listSymbol();

    int updateByPrimaryKeySelective(SymbolDO record);

    int updateByPrimaryKey(SymbolDO record);
}