package com.example.springoauth2.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_role_menu
 * @author 
 */
@Data
public class SysRoleMenu implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    private static final long serialVersionUID = 1L;
}