package com.example.springsecurity.security;

import com.alibaba.fastjson.JSON;
import io.ebang.it.common.general.utils.GeneralResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 *  没有登录时的响应
 */
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        GeneralResponse<Object> generalResponse = GeneralResponse.error(401, "not logged in");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 响应401 暂未登录或session已经过期
        response.getWriter().println(JSON.toJSON(generalResponse));
        response.getWriter().flush();
      //  throw new ErpApiException(ResponseCode.ERROR_PASSWORD_CODE,"not.logged.in");
    }
}
