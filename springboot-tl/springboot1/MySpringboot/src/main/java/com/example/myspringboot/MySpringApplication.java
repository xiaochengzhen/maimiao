package com.example.myspringboot;

import com.example.myspringboot.service.WebServer;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/11 15:25
 */
public class MySpringApplication {
    /**
    1、创建一个Spring容器
    2、创建Tomcat对象
    3、生成DispatcherServlet对象，并且和前面创建出来的Spring容器进行绑定
    4、将DispatcherServlet添加到Tomcat中
    5、启动Tomcat
     */
    public static void run(Class calzz) {
        //创建spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(calzz);
        applicationContext.refresh();
        //开启tomcat
        WebServer webServer = getWebServed(applicationContext);
        webServer.start(applicationContext);
    }

    public static WebServer getWebServed(WebApplicationContext applicationContext) {
        Map<String, WebServer> beansOfType = applicationContext.getBeansOfType(WebServer.class);
        if (beansOfType.isEmpty()) {
            throw new NullPointerException();
        }
        if (beansOfType.size() > 1) {
            throw new IllegalStateException();
        }
        return beansOfType.values().stream().findFirst().get();
    }



}
