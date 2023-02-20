package com.example.authserver.model;

import lombok.Data;

/**
 * sys_menu
 * @author 
 */
@Data
public class SysMenu extends BaseModel{

    /**
     * 上级ID，一级菜单为0
     */
    private Integer pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
     */
    private String permissions;

    /**
     * 类型   0：菜单   1：按钮
     */
    private Byte type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

}