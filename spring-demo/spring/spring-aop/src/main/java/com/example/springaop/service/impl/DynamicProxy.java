package com.example.springaop.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/25 16:48
 */
public class DynamicProxy {

    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    public Object dynamic() {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = method.invoke(object, args);
                return result;
            }
        };
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),invocationHandler);
    }

}
