package com.example.springsecurity.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.ebang.it.auth.core.security.SecurityUtils.*;

/***
 * 参数过滤器
 */
public class ParamsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Integer companyId = null;
        String method = request.getMethod();
        if (StringUtils.isNotBlank(method)) {
            switch (method) {
                case "GET" :
                    companyId = getCompanyId(request);
                    break;
                case "DELETE" :
                    companyId = deleteCompanyId(request);
                    break;
                default:
                    break;
            }
        }
        if (method.equals("PUT") || method.equals("POST")) {
            RequestWrapper requestWrapper = new RequestWrapper(request);
            if (null == requestWrapper) {
                filterChain.doFilter(request,response);
            } else {
                String bodyString = requestWrapper.getBodyString();
                if (StringUtils.isNotBlank(method)) {
                    switch (method) {
                        case "POST" :
                            companyId = postCompanyId(request, bodyString);
                            break;
                        case "PUT" :
                            companyId = putCompanyId(request, bodyString);
                            break;
                        default:
                            break;
                    }
                }
                requestWrapper.setAttribute("companyId", companyId);
                filterChain.doFilter(requestWrapper,response);
            }
        } else {
            request.setAttribute("companyId", companyId);
            filterChain.doFilter(request,response);
        }
    }

}
