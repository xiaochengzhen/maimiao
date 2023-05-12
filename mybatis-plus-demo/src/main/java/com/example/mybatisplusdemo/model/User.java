package com.example.mybatisplusdemo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 主键ID
     */
    @OrderBy(asc = false, sort = 1)
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField(updateStrategy=FieldStrategy.NOT_EMPTY)
    private String userName;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age1;

    /**
     * 邮箱
     */
    private String email;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //@TableField(update = "%s+1")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    private Integer companyId;

    private static final long serialVersionUID = 1L;
}