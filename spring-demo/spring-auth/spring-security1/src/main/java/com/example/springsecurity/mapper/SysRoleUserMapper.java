package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.model.SysMenu;
import com.example.springsecurity.model.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
}