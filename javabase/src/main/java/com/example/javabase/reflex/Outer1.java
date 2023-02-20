package com.example.javabase.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhangquanit
 */
public class Outer1 {

    private Integer age;
    private String name;
    // 内部类
    public static class InnerClass {
        public InnerClass() {

        }

        public void fun() {

        }
    }

    // 内部接口
    public interface InnerInterface {

    }


    //getFields：获取当前类或父类或父接口的 public 修饰的字段。
    //getDeclaredFields：获取当前类的所有字段，包括 protected/默认/private 修饰的字段；不包括父类public 修饰的字段。

    //getMethods：获取当前类或父类或父接口的 public 修饰的字段；包含接口中 default 修饰的方法 (JDK1.8)。
    //getDeclaredMethods： 获取当前类的所有方法；包括 protected/默认/private 修饰的方法；不包括父类 、接口 public 修饰的方法。
    public static void main(String[] args) {

        Class<?> declaringClass = InnerClass.class.getDeclaringClass();
        System.out.println("InnerClass定义所在类为："+declaringClass);//class reflect.Outer

        declaringClass =InnerInterface.class.getDeclaringClass();
        System.out.println("InnerInterface定义所在类为："+declaringClass);//class reflect.Outer

        Class<?> enclosingClass = InnerClass.class.getEnclosingClass();
        System.out.println(enclosingClass);
        Class<? extends Class> aClass = InnerClass.class.getClass();
        System.out.println(aClass);

        Field[] declaredFields = Outer1.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        System.out.println("================");
        Method[] declaredMethods = Outer1.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        System.out.println("================");
        Method[] methods = Outer1.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("================");
        Field[] fields = Outer1.class.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

}