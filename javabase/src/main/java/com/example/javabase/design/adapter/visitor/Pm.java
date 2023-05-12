package com.example.javabase.design.adapter.visitor;

import java.util.Random;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 17:00
 */
public class Pm extends Employee{
    public Pm(String name) {
        super(name);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    public Integer getProject() {
        return new Random().nextInt(10);
    }
}
