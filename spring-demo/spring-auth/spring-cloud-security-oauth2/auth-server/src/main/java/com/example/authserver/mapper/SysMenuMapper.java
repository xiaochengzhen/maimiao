package com.example.authserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authserver.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}