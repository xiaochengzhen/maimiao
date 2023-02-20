package com.example.authserver.controller;

import com.example.authserver.model.SysUser;
import com.example.authserver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/19 08:15
 */
@RestController
@RequestMapping
public class TestController {

    @Resource
    private SysUserService sysUserService;
    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * @description 用户注册
     * @author xiaobo
     * @param sysUser
     * @return io.ebang.it.common.general.utils.GeneralResponse<io.ebang.it.data.auth.db.model.SysUser>
     * @date 2022/6/20 10:16
     */
    @PostMapping("/register")
    public SysUser register(@RequestBody SysUser sysUser) {
        SysUser user = sysUserService.register(sysUser);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<OAuth2AccessToken> login(Principal principal, @RequestParam
            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> accessToken = tokenEndpoint.getAccessToken(principal, parameters);
        return accessToken;
    }

    @GetMapping("/oauth")
    public String test() {
        return "test";
    }

    @GetMapping("/index")
    public String index() {
        String username = getUsername();
        return "欢迎登录："+username;
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess() {
        String username = getUsername();
        return "欢迎登录："+username;
    }

    @PostMapping("/loginError")
    public String loginError() {
        return "用户名或密码错误";
    }


    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = "";
        if (principal != null) {
            if (principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                username = userDetails.getUsername();
            } else {
                username = principal.toString();
            }
        }
        return username;
    }
}
