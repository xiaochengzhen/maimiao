package com.example.craw.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @description 利润表对应关系注解
 * @author xiaobo
 * @date 2023/12/4 8:14
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IncomeKeyAnnotation {

    String value() default "";
    String enName() default "";
    String zhName() default "";
}
