package com.example.springoauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springoauth2.model.SysRoleMenu;
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