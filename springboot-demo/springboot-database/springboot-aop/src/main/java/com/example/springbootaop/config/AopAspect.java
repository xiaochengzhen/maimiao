package com.example.springbootaop.config;

import com.example.springbootaop.annotion.AopAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 14:07
 */
@Component
@Aspect
public class AopAspect {

    @Pointcut("@annotation(annotation)")
    public void bf(AopAnnotation annotation) {

    }

    @Before("bf(annotation)")
    public void before(JoinPoint joinPoint, AopAnnotation annotation) {
        DynamicDataSource.name.set(annotation.value());
    }
}
