package com.example.myspringboot.annotion;


import com.example.myspringboot.condition.MyOnClassCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(MyOnClassCondition.class)
public @interface MyCondtionalOnClass {

    String value() default "";
}
