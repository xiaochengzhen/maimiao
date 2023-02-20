package com.example.filterinterceptor.listener.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/4 16:33
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequestListener.super.requestDestroyed(sre);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequestListener.super.requestInitialized(sre);
    }
}
