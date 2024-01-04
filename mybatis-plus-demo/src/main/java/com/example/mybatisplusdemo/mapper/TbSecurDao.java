package com.example.mybatisplusdemo.mapper;

import com.example.mybatisplusdemo.model.TbSecur;

public interface TbSecurDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbSecur record);

    int insertSelective(TbSecur record);

    TbSecur selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbSecur record);

    int updateByPrimaryKey(TbSecur record);
}