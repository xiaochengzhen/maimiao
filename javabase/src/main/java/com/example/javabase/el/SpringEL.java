package com.example.javabase.el;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author xiaobo
 * @description
 * @date 2022/10/14 09:33
 */
public class SpringEL {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
       /* Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();*/

       /* Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        System.out.println(bytes);*/

        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        Integer  length = (Integer) exp.getValue();
        System.out.println(length);
    }
}
