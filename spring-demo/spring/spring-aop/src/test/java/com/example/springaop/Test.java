package com.example.springaop;

import com.example.springaop.model.User;
import com.example.springaop.service.UserService;
import com.example.springaop.service.impl.DynamicProxy;
import com.example.springaop.service.impl.ProxyUser;
import com.example.springaop.service.impl.UserServiceImpl;
import org.junit.Before;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/25 16:17
 */
public class Test {

    ApplicationContext applicationContext;
    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:web.xml");
    }

    @org.junit.Test
    public void test1() {
        UserService userService = applicationContext.getBean("userServiceImpl",UserService.class);
        User play = userService.play();
        System.out.println("返回结果======"+play.getName());
    }

    @org.junit.Test
    public void test2() {
        UserService userService = applicationContext.getBean(ProxyUser.class);
        userService.play();
    }

    /*
    *
    * jdk
    * */
    @org.junit.Test
    public void test3() {
        UserServiceImpl userService = new UserServiceImpl();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("123");
                Object result = method.invoke(userService, args);
                System.out.println("456");
                return result;
            }
        };
        UserService o = (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), userService.getClass().getInterfaces(), invocationHandler);
        o.play();
    }

    /*
    * jdk
    * */
    @org.junit.Test
    public void test4() {
        UserServiceImpl userService = new UserServiceImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(userService);
        UserService dynamic = (UserService) dynamicProxy.dynamic();
        dynamic.play();
    }

    /**
     * @description cglib
     * @author xiaobo
     * @return void
     * @date 2022/8/25 17:21
     */
    @org.junit.Test
    public void test5() {
        UserServiceImpl userService = new UserServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(userService.getClass().getClassLoader());
        enhancer.setSuperclass(userService.getClass());
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("111");
                Object invoke = method.invoke(userService, objects);
                System.out.println("222");
                return invoke;
            }
        };
        enhancer.setCallback(methodInterceptor);
        UserService dynamic = (UserService) enhancer.create();
        dynamic.play();
    }
}
