package com.example.javabase.design.adapter.jieshiqi;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 18:27
 */
public class Cal {
    private Expression left;
    private Expression right;
    private Integer result;
    public Cal(String expression) {
        this.parse(expression);
    }
    private Integer parse(String expression) {
        // 获取表达式元素
        String [] elements = expression.split(" ");
        for (int i = 0; i < elements.length; i++) {
            String element = elements[i];
            // 判断是否是运算符号
            if (OperatorUtils.isOperator(element)) {
                // 运算符号的右边就是右终结符
                right = new NumberTerminal(Integer.valueOf(elements[++i]));
                //计算结果
                result = OperatorUtils.getNonTerminal(left, right, element).interpret();
                // 计算结果重新成为左终结符
                left = new NumberTerminal(result);
            } else {
                left = new NumberTerminal(Integer.valueOf(element));
            }
        }
        return result;
    }
    public Integer cal() {
        return result;
    }
}
