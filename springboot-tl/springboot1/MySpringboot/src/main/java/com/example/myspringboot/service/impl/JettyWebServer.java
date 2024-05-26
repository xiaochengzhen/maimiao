package com.example.myspringboot.service.impl;

import com.example.myspringboot.service.WebServer;
import org.springframework.web.context.WebApplicationContext;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/13 8:43
 */
public class JettyWebServer implements WebServer {
    @Override
    public void start(WebApplicationContext applicationContext) {
        System.out.println("=================启动Jetty===========================");
    }
}
