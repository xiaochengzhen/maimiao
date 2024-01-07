package com.example.mysql.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_sec_account
 * @author 
 */
@Data
public class SecAccountDO implements Serializable {
    private Long id;

    /**
     * 渠道 0:自有渠道 1:IB 2:港交所
     */
    private Integer routeId;

    /**
     * 渠道账户类型 0:渠道主账户 1:现金多头股票池 2:保证金多头股票池 3:保证金空头股票池 4:ND账户
     */
    private Integer accountType;

    /**
     * 证券帐号
     */
    private String accountId;

    /**
     * 状态 0:未使用 1:已使用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}