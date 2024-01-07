package com.example.mysql.mapper;

import com.example.mysql.model.SecAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecAccountDO record);

    int insertSelective(SecAccountDO record);

    SecAccountDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecAccountDO record);

    int updateByPrimaryKey(SecAccountDO record);
}