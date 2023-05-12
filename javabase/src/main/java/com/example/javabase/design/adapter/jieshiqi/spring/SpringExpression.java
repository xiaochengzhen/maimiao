package com.example.javabase.design.adapter.jieshiqi.spring;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 18:38
 */
public class SpringExpression {

    public static void main(String[] args) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        org.springframework.expression.Expression expression = expressionParser.parseExpression("10 + 20 + 30 * 4");
        Integer value = expression.getValue(Integer.class);
        System.out.println(value); // 150
        expression = expressionParser.parseExpression("(10+20+30)*4");
        value = expression.getValue(Integer.class);
        System.out.println(value); // 240
    }
}
