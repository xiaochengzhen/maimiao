package com.example.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplusdemo.model.TableUser;
import com.example.mybatisplusdemo.model.TblRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblRoleMapper extends BaseMapper<TblRole> {

}