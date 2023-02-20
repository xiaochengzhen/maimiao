package com.example.filter.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/4 11:56
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("==============filter 前=================");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("==============filter 后=================");
    }

    @Override
    public void destroy() {
    }
}
