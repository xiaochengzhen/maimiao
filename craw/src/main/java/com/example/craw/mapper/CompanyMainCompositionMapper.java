package com.example.craw.mapper;

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

    int insertBatch(List<CompanyMainCompositionDO> list);

    int update(CompanyMainCompositionDO record);

    CompanyMainCompositionDO selectByQuarter(@Param("symbol") String symbol, @Param("quarter") String quarter);

    List<String> listQuarter(@Param("symbol") String symbol, @Param("type") String type);

    List<CompanyMainCompositionDO> list(@Param("symbol") String symbol, @Param("quarter") String quarter, @Param("type") String type);

}