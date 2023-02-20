package com.example.springsecurity.service;

import com.example.springsecurity.model.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 09:59
 */
public interface SysUserService extends UserDetailsService {

    SysUser createUser(SysUser sysUser);

}
