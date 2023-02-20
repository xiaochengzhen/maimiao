package com.example.springaop.config;

import com.example.springaop.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/25 16:22
 */
@Aspect
@Component
public class AspectTest {
    //访问修饰符  返回值  包名 类型 方法名  参数
    //execution
    //within 只能到类
    //annotion
    @Pointcut("execution(public * com.example.springaop..*.*(..))")
    public void test() {

    }

    @Before("test()")
    public void before(JoinPoint joinPoint) {
        System.out.println("前置");
    }

    @After("test()")
    public void after(JoinPoint joinPoint) {
        System.out.println("后置");
    }

    @AfterReturning(value = "test()", returning="a")
    public void afterReturning(JoinPoint joinPoint, User a) {
        System.out.println("后置返回");
        System.out.println("后置返回=="+a.getName());
        a.setName("maimiao");
    }

    @AfterThrowing("test()")
    public void afterThrowing() {
        System.out.println("后置异常");
    }

    @Around("test()")
    public void around() {
        System.out.println("环绕");
    }


}
