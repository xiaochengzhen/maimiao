package com.example.javabase.design.adapter.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 17:01
 */
public class Report {

    private List<Employee> employeeList;

    public Report(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    public void showReport(Visitor visitor) {
        for (Employee employee : employeeList) {
            employee.accept(visitor);
        }
    }
    // 测试
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Engineer("工程师A"));
        employeeList.add(new Engineer("工程师B"));
        employeeList.add(new Engineer("项目经理A"));
        employeeList.add(new Engineer("工程师C"));
        employeeList.add(new Engineer("工程师D"));
        employeeList.add(new Engineer("项目经理B"));
        Report report = new Report(employeeList);
        System.out.println("=============CEO==============");
        report.showReport(new CeoVisitor());
        System.out.println("=============CTO==============");
        report.showReport(new CtoVisitor());
    }

}
