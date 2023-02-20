package com.example.lamdademo.service;

import com.example.lamdademo.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 *
 * java lambda表达式为：(parameters) -> expression或(parameters) ->{ statements; }。Lambda表达式也可称为闭包，是一个匿名函数，是对匿名函数的简写形式，
 * 我们可以把 Lambda表达式理解为是一段可以传递的代码（将代码像数据一样进行传递），可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。
 *
 * Lambda 表达式由三部分组成：1、形参列表：形参列表允许省略类型，如果形参列表中只有一个参数，形参列表的圆括号也可以省略；2、箭头（->）：通过英文画线和大于符号组成；
 * 3、代码块：如果代码块只有一条语句，花括号可以省略。Lambda 代码块只有一条 return 语句，可以省略 return 关键字，Lambda 表达式会自动返回这条语句的值作为返回值。
 *
 * lambda表达式的重要特征：
 *
 * 1、可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 *
 * 2、可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 *
 * 3、可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 *
 * 4、可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定表达式返回了一个数值。
 *
 *
 * @author xiaobo
 * @description
 * @date 2022/6/29 16:49
 */
public class TestService {

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        Callable<Integer> integerCallable = () -> 5;
        try {
            Integer call = integerCallable.call();
            System.out.println(call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println(addition.operation(1, 2));

        Test1 test1 = (int a)->1;
        int math = test1.math(5);
        System.out.println(math);
        Tst tst = (String s)-> System.out.println(s);
        tst.tts("1");

    }

    interface  Tst {
        void tts(String s);
    }

    public void testSteam() {
        List<Student> list = new ArrayList<>();
        list.stream().filter(student -> {
            if (student.getAge() != 0){
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        list.stream().collect(Collectors.toMap(student -> student.getName(), student -> student.getAge()));
        list.stream().collect(Collectors.toMap(student -> student.getName(), student -> student));
        list.stream().collect(Collectors.toMap(student -> student, student -> student));
        list.stream().collect(Collectors.toMap(Student::getAge, student -> student));
        list.stream().map(student -> student.getAge()*2).collect(Collectors.toList());
    }
}
