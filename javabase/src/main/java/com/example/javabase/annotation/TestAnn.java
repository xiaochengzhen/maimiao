package com.example.javabase.annotation;

import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/7 11:20
 */
@Data
public class TestAnn {

    @TestAnnotation(annName = 1)
    private String name;


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<TestAnn> testAnnClass = TestAnn.class;
        Field[] declaredFields = testAnnClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            TestAnnotation annotation = declaredField.getAnnotation(TestAnnotation.class);
            System.out.println(annotation.annName());
            // 示例：通过反射修改注解属性值
            Class<? extends Annotation> annotationType = annotation.annotationType();
            Method method = annotationType.getDeclaredMethod("annName");
            method.setAccessible(true);
            // 修改注解属性值
            method.invoke(annotation, 1);

            System.out.println(annotation.annName());



        }


    }
}
