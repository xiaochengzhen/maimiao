package com.example.springoauth2.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_role_user
 * @author 
 */
@Data
public class SysRoleUser implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long userId;

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