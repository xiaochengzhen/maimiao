package com.example.reptile.webmagic.getrequest.annonation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2024/12/26 8:16
 */
@Mapper
public interface SymbolMapper {

    @Select("select office_symbol from tb_symbol where is_hot = 1")
    List<String> listSymbol();

}
