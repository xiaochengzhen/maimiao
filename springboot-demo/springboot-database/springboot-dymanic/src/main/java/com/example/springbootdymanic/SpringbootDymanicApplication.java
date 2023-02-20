package com.example.springbootdymanic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy=true)//暴露代理，把动态代理暴露在线程 threadLock中 可以通过  AopContext.currentPorxy 获取代理对象
@SpringBootApplication
public class SpringbootDymanicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDymanicApplication.class, args);
    }

}
