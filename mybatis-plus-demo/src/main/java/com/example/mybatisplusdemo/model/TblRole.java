package com.example.mybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.panpan.maimiaoautoconfigure.annotation.CheckUniqueField;
import com.panpan.maimiaoautoconfigure.annotation.QuoteField;
import lombok.Data;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/21 10:23
 */
@Data
@CheckUniqueField(value = "role_code", tableName = "tbl_role", dataSourceName = "mpSource", tips = "角色编码已存在")
public class TblRole {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String roleCode;
    private String roleName;

    @TableField(exist = false)
    @QuoteField(value = "userId", associatedField = "id", getField = "user_name", tableName = "user", dataSourceName ="mpSource")
    private String userName;
}
