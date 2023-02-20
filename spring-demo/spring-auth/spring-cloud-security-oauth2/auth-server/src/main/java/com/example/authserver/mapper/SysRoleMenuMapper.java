package com.example.authserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authserver.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
}