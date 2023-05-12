package com.example.javabase.design.adapter.visitor;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:56
 */
public interface Visitor {
    void visit(Engineer engineer);
    void visit(Pm pm);
}
