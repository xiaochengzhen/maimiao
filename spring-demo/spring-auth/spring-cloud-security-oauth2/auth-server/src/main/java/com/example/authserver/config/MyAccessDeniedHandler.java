package com.example.authserver.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xiaobo
 * @description ExceptionTranslationFilter  操作认证失败的处理，扩展点
 * @date 2022/6/19 18:08
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.setHeader("Content-Type","application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("权限不足，请联系管理员");
        out.flush();
        out.close();
    }
}
