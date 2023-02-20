package com.example.authserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authserver.model.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
}