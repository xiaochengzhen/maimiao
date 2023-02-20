package com.example.springoauth2.service;

import com.example.springoauth2.model.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 09:59
 */
public interface SysUserService extends UserDetailsService {

    SysUser createUser(SysUser sysUser);

}
