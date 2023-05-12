package com.example.javabase.design.adapter.jieshiqi;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 18:21
 */
public class NumberTerminal implements Expression{
    private int number;

    public NumberTerminal(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}
