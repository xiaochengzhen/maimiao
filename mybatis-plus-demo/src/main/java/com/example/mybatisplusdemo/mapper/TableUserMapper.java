package com.example.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplusdemo.model.TableUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableUserMapper extends BaseMapper<TableUser> {
    int deleteByPrimaryKey(Long id);

    int insertTableUser(TableUser record);

    int test(TableUser record);

    int insertSelective(TableUser record);

    TableUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TableUser record);

    int updateByPrimaryKey(TableUser record);
}