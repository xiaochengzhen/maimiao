package com.example.springsecurity.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

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