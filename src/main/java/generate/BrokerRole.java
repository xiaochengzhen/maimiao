package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_broker_role
 * @author 
 */
@Data
public class BrokerRole implements Serializable {
    private Long id;

    /**
     * 券商id
     */
    private Long bid;

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否为超级管理员[0: 不是 | 1: 是]
     */
    private Byte isAdmin;

    /**
     * 角色类型 0:自定义 1：系统（包括超管和客户主任）
     */
    private Byte roleType;

    /**
     * 状态0=禁用; 1=启用;-1=删除; 2=禁用（审批中）;3=启用（审批中）
     */
    private Byte status;

    /**
     * 删除状态0=未删除;1=已删除;2=已删除（审批中）
     */
    private Byte deleteStatus;

    /**
     * 权限变更状态：0=未变更;1=已变更;2=已变更（审批中）
     */
    private Byte permissionChangeStatus;

    /**
     * 操作员变更状态：0=未变更;1=已变更;2=已变更（审批中）
     */
    private Byte roleAssignOperatorStatus;

    /**
     * 权限变更审批中的关联权限
     */
    private Object approvingPermission;

    /**
     * 操作员变更审批中的关联操作员
     */
    private Object approvingOperator;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 操作员类型[0: 系统 | 1: C端用户 | 2: Broker Admin]
     */
    private Byte operatorType;

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