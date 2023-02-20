package com.example.springoauth2.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_user
 * @author 
 */
@Data
public class SysUser implements Serializable {
    /**
     * id
     */
    @TableId(type= IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 性别   0：男   1：女    2：保密
     */
    private Byte gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 超级管理员   0：否   1：是
     */
    private Byte superAdmin;

    /**
     * 状态  0：停用   1：正常
     */
    private Byte status;

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