package com.example.authserver.service;

import com.example.authserver.model.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 09:59
 */
public interface SysUserService extends UserDetailsService {

    /**
     * @description 注册
     * @author xiaobo
     * @param 'sysUser
     * @return void
     * @date 2022/6/16 8:24
     */
    SysUser register(SysUser sysUser);

}
