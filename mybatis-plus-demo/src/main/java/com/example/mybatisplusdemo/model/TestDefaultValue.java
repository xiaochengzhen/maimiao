package com.example.mybatisplusdemo.model;

import com.panpan.maimiaoautoconfigure.annotation.DefaultValueField;
import com.panpan.maimiaoautoconfigure.annotation.DefaultValueFields;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/13 13:02
 */
@Data
public class TestDefaultValue {
    @DefaultValueField("xiaob")
    private String name;
    @DefaultValueField("1")
    private Integer age;
    @DefaultValueField("2")
    private int age1;
    @DefaultValueField("3")
    private Byte status;
    @DefaultValueField("4")
    private byte status1;
    @DefaultValueField("5.321")
    private BigDecimal quantity;
    @DefaultValueField("6")
    private Long count;
    @DefaultValueField("7")
    private long count1;
    @DefaultValueField("8")
    private Short length;
    @DefaultValueField("9")
    private short length1;
    @DefaultValueField("true")
    private Boolean aBoolean;

    private LocalDateTime createTime;
    private Date updateTime;

    @DefaultValueFields
    private TestDefault testDefault;
    @DefaultValueFields
    private List<TestDefault> list;

    @Data
    private static  class TestDefault {
        @DefaultValueField("xiaob")
        private String name;
        @DefaultValueField("1")
        private Integer age;
        @DefaultValueField("2")
        private int age1;
        @DefaultValueField("3")
        private Byte status;
        @DefaultValueField("4")
        private byte status1;
        @DefaultValueField("5")
        private BigDecimal quantity;
        @DefaultValueField("6")
        private Long count;
        @DefaultValueField("7")
        private long count1;
        @DefaultValueField("8")
        private Short length;
        @DefaultValueField("9")
        private short length1;
        @DefaultValueField("true")
        private Boolean aBoolean;
    }
}
