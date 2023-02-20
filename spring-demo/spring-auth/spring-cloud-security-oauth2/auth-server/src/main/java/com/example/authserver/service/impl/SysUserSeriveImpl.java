package com.example.authserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.authserver.mapper.SysUserMapper;
import com.example.authserver.model.SysUser;
import com.example.authserver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 10:00
 */
@Service
public class SysUserSeriveImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null) {
            throw new RuntimeException("用户名不存在");
        }
        List<String> strings = sysUserMapper.listPermissionsByUserId(sysUser.getId());
        List<GrantedAuthority> list = new ArrayList<>();
        if (sysUser.getSuperAdmin() != null && sysUser.getSuperAdmin() == 1) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("superAdmin");
            list.add(grantedAuthority);
        }
        if (!CollectionUtils.isEmpty(strings)) {
            for (String string : strings) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(string);
                list.add(grantedAuthority);
            }
        }
        UserDetails userDetails = new User(sysUser.getUsername(), sysUser.getPassword(),
                list);
        return userDetails;
    }

    @Override
    public SysUser register(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserMapper.insert(sysUser);
        return sysUserMapper.selectById(sysUser.getId());
    }


}
