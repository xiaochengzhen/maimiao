package com.example.springsecurity.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.ebang.it.data.auth.db.dao.user.SysUserMapper;
import io.ebang.it.data.auth.db.model.menu.SysMenu;
import io.ebang.it.data.auth.db.model.user.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/30 13:36
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SecurityResource securityResource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String usernameReq  = username;
        Integer companyId = null;
        String[] split = StringUtils.split(username, "&");
        if (split.length > 1) {
            usernameReq = split[0];
            companyId = Integer.valueOf(split[1]);
        }
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUsername, usernameReq);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        List<String> menuId = new ArrayList<>();
        if (sysUser != null) {
            List<SysMenu> sysMenus = new ArrayList<>();
            if (sysUser.getSuperAdmin() != null && sysUser.getSuperAdmin() == 1) {
                sysMenus = securityResource.listSysMenuByUserId(null, null);
            } else {
                sysMenus = securityResource.listSysMenuByUserId(sysUser.getId(), companyId);
            }
            if (!CollectionUtils.isEmpty(sysMenus)) {
                menuId = sysMenus.stream().map(s->String.valueOf(s.getId())).collect(Collectors.toList());
            }
        }
        UserDetails userDetails = new UserDetailsImpl(sysUser, menuId);
        return userDetails;
    }
}
