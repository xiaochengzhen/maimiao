package com.example.springoauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springoauth2.model.SysRoleUser;
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