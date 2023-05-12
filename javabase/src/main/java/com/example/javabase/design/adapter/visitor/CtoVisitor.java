package com.example.javabase.design.adapter.visitor;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:57
 */
public class CtoVisitor implements Visitor{
    @Override
    public void visit(Engineer engineer) {
        System.out.println(engineer.getName() + "工作内容:" + engineer.getCodeLine() + "行代码");
    }

    @Override
    public void visit(Pm pm) {
        System.out.println(pm.getName() + "工作内容:" + pm.getProject() + "个项目");
    }
}
