package com.example.javabase.design.adapter.visitor;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:57
 */
public class CeoVisitor implements Visitor{
    @Override
    public void visit(Engineer engineer) {
        System.out.println(engineer.getName() + "KPI为:" + engineer.getKpi());
    }

    @Override
    public void visit(Pm pm) {
        System.out.println(pm.getName() + "KPI为:" + pm.getKpi());
    }
}
