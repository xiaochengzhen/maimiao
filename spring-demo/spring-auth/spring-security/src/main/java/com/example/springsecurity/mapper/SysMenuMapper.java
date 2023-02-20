package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}