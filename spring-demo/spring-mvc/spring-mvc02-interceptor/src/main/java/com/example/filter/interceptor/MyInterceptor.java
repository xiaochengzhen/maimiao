package com.example.filter.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/4 11:33
 */
public class MyInterceptor implements HandlerInterceptor {

    /*
    * 请求之前触发
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=======请求之前触发=============");
        return true;
    }

    /*
    * 方法执行之后，视图渲染之前， 方法异常，不执行
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("========方法执行之后，视图渲染之前， 方法异常，不执行============");
    }

    /*
    * 视图渲染之后，如果方法执行异常，也会执行
    * */
    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("==========视图渲染之后，如果方法执行异常，也会执行==========");
    }
}
