package com.example.authserver.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xiaobo
 * @description 基数信息的实体类
 * @date 2022/6/23 09:02
 */
@Data
public class BaseModel implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updater;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @OrderBy
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

   // @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


    protected static final long serialVersionUID = 1L;

    public BaseModel() {
    }

    public BaseModel(Integer id) {
        this.id = id;
    }
}
