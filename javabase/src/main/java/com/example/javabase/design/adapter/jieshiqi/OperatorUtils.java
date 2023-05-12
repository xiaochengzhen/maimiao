package com.example.javabase.design.adapter.jieshiqi;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 18:28
 */
public class OperatorUtils {
    // 判断是不是非终结符
    public static boolean isOperator(String symbol) {
        return symbol.equals("+") || symbol.equals("-") || symbol.equals("*")|| symbol.equals("/");
    }
    // 简单工厂
    public static NonTerminal getNonTerminal(Expression left, Expression right, String symbol) {
        if (symbol.equals("+")) {
            return new PlusNonTerminal(left, right);
        } else if (symbol.equals("-")) {
            return new MinusNonTerminal(left, right);
        } else if (symbol.equals("*")) {
            return new MclNonTerminal(left, right);
        } else if (symbol.equals("/")) {
            return new DivisionNonTerminal(left, right);
        }
        return null;
    }
    // 测试
    // PS：此处进行的逻辑仅仅实现从左到右运算，并没有先乘除后加减的逻辑
    public static void main(String[] args) {
        System.out.println(new Cal("10 + 20 - 40 * 60").cal()); // -600
        System.out.println(new Cal("20 + 50 - 60 * 2").cal()); // 20
    }
}
