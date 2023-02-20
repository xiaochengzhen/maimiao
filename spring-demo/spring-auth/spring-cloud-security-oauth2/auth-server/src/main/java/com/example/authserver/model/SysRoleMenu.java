package com.example.authserver.model;

import lombok.Data;

/**
 * sys_role_menu
 * @author 
 */
@Data
public class SysRoleMenu extends BaseModel{

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;

}