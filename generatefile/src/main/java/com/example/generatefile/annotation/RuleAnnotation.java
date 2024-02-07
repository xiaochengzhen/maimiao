package com.example.generatefile.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 15:18
 */
@Target({ElementType.TYPE, ElementType.FIELD})@Retention(RetentionPolicy.RUNTIME)
public @interface RuleAnnotation {

    String value() default "";

    //补位方向，L  左， R  右
    String dirention() default "R";}
