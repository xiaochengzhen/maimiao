package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.mapper.SysUserMapper;
import com.example.springsecurity.model.SysUser;
import com.example.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 10:00
 */
@Service
public class SysUserSeriveImpl implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;

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
        for (String string : strings) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(string);
            list.add(grantedAuthority);
        }
        UserDetails userDetails = new User(sysUser.getUsername(), passwordEncoder.encode(sysUser.getPassword()),
                    list);
        return userDetails;
    }

    public static void main(String[] args) {
        String pw = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(pw);
    }
}
