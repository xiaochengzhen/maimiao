package com.example.easypoi.model;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @description
 * @author xiaobo
 * @date 2023/5/12 11:12
 */
@ExcelTarget("student")
@Data
public class Student implements IExcelModel, IExcelDataModel {

    @NotBlank(message = "name2")
    @Excel(name = "name1")
    private String name;
    @NotNull
    @Excel(name = "age")
    private Integer age;
    @Excel(name = "status")
    private Integer status;
    @Excel(name = "rate")
    private BigDecimal rate;
    private String errorMsg;
    private Integer rowNum;

}
