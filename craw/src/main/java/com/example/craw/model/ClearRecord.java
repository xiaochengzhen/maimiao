package com.example.craw.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_clear_record
 * @author 
 */
@Data
public class ClearRecord implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 清算日期
     */
    private String clearingDate;

    /**
     * 清算周期
     */
    private String clearCycle;

    /**
     * 0待对账 1待入账 2已完成 3已延期 4:待计息
     */
    private Byte clearStatus;

    /**
     * 交易匹配状态 0待对账 1已完成
     */
    private Byte tradeMatchStatus;

    /**
     * 渠道账户持仓对账状态 0待对账 1已完成
     */
    private Byte routePositionStatus;

    /**
     * 渠道账户现金对账状态 0待对账 1已完成
     */
    private Byte routeCashStatus;

    /**
     * 用户账户持仓对账状态 0待对账 1已完成
     */
    private Byte clientPositionStatus;

    /**
     * 用户账户现金对账状态 0待对账 1已完成
     */
    private Byte clientCashStatus;

    /**
     * 融资计息状态 0:待计息 1:计息中 2:计息完成
     */
    private Byte financingInterestCalculationStatus;

    /**
     * 融券计息状态 0:待计息 1:计息中 2:计息完成
     */
    private Byte stockBorrowInterestCalculationStatus;

    /**
     * 清算入账状态 0待入账 1入账中 2已完成
     */
    private Byte dayEndBalanceStatus;

    /**
     * 业务配置id,多个用英文逗号隔开
     */
    private String businessTimeIds;

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