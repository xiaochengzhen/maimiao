package com.example.authserver.model;

import lombok.Data;

/**
 * sys_role_user
 * @author 
 */
@Data
public class SysRoleUser extends BaseModel {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 用户ID
     */
    private Integer userId;

}