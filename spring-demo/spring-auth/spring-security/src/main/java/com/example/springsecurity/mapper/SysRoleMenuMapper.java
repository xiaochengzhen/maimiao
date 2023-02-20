package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.model.SysMenu;
import com.example.springsecurity.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
}