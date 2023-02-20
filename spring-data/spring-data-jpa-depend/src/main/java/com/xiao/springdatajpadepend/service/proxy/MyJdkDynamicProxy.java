package com.xiao.springdatajpadepend.service.proxy;

import org.springframework.context.annotation.ComponentScan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/29 09:23
 */
@ComponentScan
public class MyJdkDynamicProxy implements InvocationHandler {

    private Object targetObject;

    public MyJdkDynamicProxy() {
    }

    public MyJdkDynamicProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method method1 = targetObject.getClass().getMethod(method.getName(), method.getParameterTypes());
        Object invoke = method1.invoke(targetObject, args);
        return invoke;
    }
}
