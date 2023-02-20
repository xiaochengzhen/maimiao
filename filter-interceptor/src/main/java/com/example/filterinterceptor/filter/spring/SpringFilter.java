package com.example.filterinterceptor.filter.spring;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/4 14:47
 */
@WebFilter("/")
public class SpringFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter的doFilter方法");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter的响应之后方法");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
