package com.example.springoauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springoauth2.model.SysMenu;
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