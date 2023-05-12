package com.example.javabase.design.adapter.jieshiqi;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 18:25
 */
public class PlusNonTerminal extends NonTerminal implements Expression {
    public PlusNonTerminal(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}
