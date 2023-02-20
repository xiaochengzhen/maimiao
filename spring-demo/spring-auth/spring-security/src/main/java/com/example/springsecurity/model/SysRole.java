package com.example.springsecurity.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * sys_role
 * @author 
 */
@Data
public class SysRole implements Serializable {
    /**
     * id
     */
    private Long id;

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
    private Long deptId;

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