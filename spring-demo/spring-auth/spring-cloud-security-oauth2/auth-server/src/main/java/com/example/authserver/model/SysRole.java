package com.example.authserver.model;

import lombok.Data;

/**
 * sys_role
 * @author 
 */
@Data
public class SysRole extends BaseModel {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Integer deptId;

}