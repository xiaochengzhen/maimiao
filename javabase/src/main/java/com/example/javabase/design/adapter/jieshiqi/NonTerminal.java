package com.example.javabase.design.adapter.jieshiqi;

import lombok.AllArgsConstructor;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 18:24
 */
@AllArgsConstructor
public abstract class NonTerminal implements Expression{
    protected Expression left;
    protected Expression right;
}
