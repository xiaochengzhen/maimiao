package com.example.craw;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * tb_company_share_active
 * @author 
 */
@Data
public class CompanyShareActiveDO implements Serializable {
    /**
     * ID标识
     */
    private Long id;

    /**
     * 标的代码
     */
    private String symbol;

    /**
     * 日期
     */
    private String date;

    /**
     * 类型 1:机构 2:个人
     */
    private Integer type;

    /**
     * 业务订单 ID
     */
    private String name;

    /**
     * 方向 1:流入 2:流出
     */
    private Integer side;

    /**
     * 变动股数
     */
    private BigDecimal quantity;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 变动后持股数量
     */
    private BigDecimal shareValue;

    /**
     * 变动后持股比例
     */
    private BigDecimal shareRatio;

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