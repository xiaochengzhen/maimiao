package com.example.javabase.design.adapter.jieshiqi;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 18:27
 */
public class DivisionNonTerminal extends NonTerminal implements Expression {
    public DivisionNonTerminal(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public int interpret() {
        return left.interpret() / right.interpret();
    }
}
