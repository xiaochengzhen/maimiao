package com.example.springsecurity.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * sys_user
 * @author 
 */
@Data
public class SysUser implements Serializable {
    /**
     * id
     */
    private Integer id;

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
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 性别  0：保密； 1：男  2：女    
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
    private String deptId;

    /**
     * 超级管理员   0：否   1：是
     */
    private Byte superAdmin;

    /**
     * 状态  0：停用   1：正常
     */
    private Byte status;

    /**
     * 用户范围：1=企业内员工； 2=临时登录
     */
    private Byte scope;

    /**
     * 删除状态：1=删除； 0=不删除
     */
    private Integer deleted;

    /**
     * 修改人
     */
    private String updater;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}