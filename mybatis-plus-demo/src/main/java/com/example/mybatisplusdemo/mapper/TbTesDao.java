package com.example.mybatisplusdemo.mapper;

import com.example.mybatisplusdemo.model.TbTes;

public interface TbTesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbTes record);

    int insertSelective(TbTes record);

    TbTes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbTes record);

    int updateByPrimaryKey(TbTes record);
}