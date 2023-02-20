package com.example.springoauth2.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_menu
 * @author 
 */
@Data
public class SysMenu implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 上级ID，一级菜单为0
     */
    private Long pid;

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

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    private static final long serialVersionUID = 1L;
}