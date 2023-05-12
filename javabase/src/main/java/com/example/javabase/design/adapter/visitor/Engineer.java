package com.example.javabase.design.adapter.visitor;

import java.util.Random;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:59
 */
public class Engineer extends Employee{
    public Engineer(String name) {
        super(name);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    public Integer getCodeLine() {
        return new Random().nextInt(10000);
    }
}
