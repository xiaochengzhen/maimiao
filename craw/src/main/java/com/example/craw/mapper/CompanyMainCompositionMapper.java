package com.example.craw.mapper;

import com.example.craw.dto.query.ListMainCompositionQuery;
import com.example.craw.model.CompanyMainCompositionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 主营构成的mapper
 * @author xiaobo
 * @date `2023/12/4` 8:16
 */
@Mapper
public interface CompanyMainCompositionMapper {

    int insert(CompanyMainCompositionDO record);

    int update(CompanyMainCompositionDO record);

    CompanyMainCompositionDO selectByParams(@Param("type") Integer type, @Param("symbol") String symbol, @Param("date") Long date);

    List<CompanyMainCompositionDO> list(ListMainCompositionQuery listMainCompositionQuery);

}