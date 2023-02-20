package com.example.springsecurity.security;

import io.ebang.it.data.auth.db.dao.menu.SysMenuMapper;
import io.ebang.it.data.auth.db.model.menu.SysMenu;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 获取资源信息
 * @author xiaobo
 * @date 2022/11/30 13:08
 */
@Service
public class SecurityResource {

    public static List<SysMenu> sysMenus = new ArrayList<>();
    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取资源列表通过用户
     * @return
     */
    public List<SysMenu> listSysMenuByUserId(Integer userId, Integer companyId) {
        List<SysMenu> resultList = new ArrayList<>();
        List<SysMenu> list = new ArrayList<>();
        if (userId != null && companyId == null) {
            list = sysMenuMapper.listSysMenuByUserId(userId);
        } else if (userId != null && companyId != null) {
            list =  sysMenuMapper.listSysMenuByUserIdAndComanyId(userId, companyId);
        } else {
            list = sysMenuMapper.listSysMenu();
        }
        if (!CollectionUtils.isEmpty(list)) {
            for (SysMenu sysMenu : list) {
                String path = sysMenu.getPath();
                if (StringUtils.isNotBlank(path)){
                    String[] split = StringUtils.split(path, ",");
                    for (String s : split) {
                        SysMenu menu = new SysMenu();
                        BeanUtils.copyProperties(sysMenu, menu);
                        menu.setPath(s);
                        resultList.add(menu);
                    }
                }
            }
        }
        if (userId == null && companyId == null){
            sysMenus = resultList;
        }
        return resultList;
    }
}
