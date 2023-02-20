package com.example.springsecurity.security;

import com.alibaba.fastjson.JSON;
import io.ebang.it.common.general.utils.GeneralResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *  没有权限访问时的响应处理类
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException {
        GeneralResponse<Object> unauthorized = GeneralResponse.error(403, "unauthorized");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 响应403 没有相关权限
        response.getWriter().println(JSON.toJSON(unauthorized));
        response.getWriter().flush();
       // throw new ErpApiException(ResponseCode.ERROR_PASSWORD_CODE,"unauthorized");
    }
}
