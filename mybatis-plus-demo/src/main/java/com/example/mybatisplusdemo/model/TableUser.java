package com.example.mybatisplusdemo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.github.fashionbrot.validated.annotation.Default;
import lombok.Data;

/**
 * table_user
 * @author 
 */
@Data
public class TableUser implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
   // @FieldSensitive("testStrategy")
    private String userName;

    /**
     * 年龄
     */
    @Default("456")
    private Integer age;

    /**
     * 邮箱
     */
  //  @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String email;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;
    private Long updateTe;
    private Long createTe;
}
