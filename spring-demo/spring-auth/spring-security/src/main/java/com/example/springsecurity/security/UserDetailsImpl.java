package com.example.springsecurity.security;

import io.ebang.it.data.auth.db.model.user.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/30 13:42
 */
public class UserDetailsImpl implements UserDetails {

    private SysUser sysUser;
    private List<String> list;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(SysUser sysUser, List<String> list) {
        this.sysUser = sysUser;
        this.list = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  list.stream()
                .map(menuId->new SimpleGrantedAuthority(menuId))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Integer getUserId() {
        return sysUser.getId();
    }

    public SysUser getSysUser() {
        return sysUser;
    }
}
