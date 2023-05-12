package com.example.javabase.design.adapter.visitor;

import lombok.Data;

import java.util.Random;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:58
 */
@Data
public abstract class Employee {
    private String name;
    private Integer kpi;
    public Employee(String name) {
        this.name = name;
        this.kpi = new Random().nextInt(10);
    }
    public abstract void accept(Visitor visitor);
}
