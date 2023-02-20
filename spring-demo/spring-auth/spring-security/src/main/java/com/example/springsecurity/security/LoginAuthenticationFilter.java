package com.example.springsecurity.security;

import com.alibaba.fastjson.JSON;
import io.ebang.it.common.general.entity.UserTokenDTO;
import io.ebang.it.redis.server.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.ebang.it.common.general.constant.TokenConstant.TOKEN_KEY_OPERATOR;

/***
 * OncePerRequestFilter: 能够保证过滤只执行一次
 */
public class LoginAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Integer companyId = (Integer) request.getAttribute("companyId");
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        if (StringUtils.isNotBlank(token)) {
            String value = (String) redisUtil.getValue(TOKEN_KEY_OPERATOR.apply(token));
            UserTokenDTO userTokenDTO = JSON.parseObject(value, UserTokenDTO.class);
            // 从服务器中查询
            if (userTokenDTO != null){
                String username = userTokenDTO.getUsername();
                if (companyId != null) {
                    username = username + "&"+ companyId;
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(userDetails != null){
                    // 生成springsecurity的通过认证标识
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
