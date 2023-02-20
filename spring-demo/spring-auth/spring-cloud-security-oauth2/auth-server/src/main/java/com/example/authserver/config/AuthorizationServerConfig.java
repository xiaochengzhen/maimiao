package com.example.authserver.config;

import com.example.authserver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author xiaobo
 * @description 配置授权服务器
 * @date 2022/7/19 11:17
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysUserService sysUserService;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManagerBean) //使用密码模式需 要配置
                .tokenStore(tokenStore) //指定token存储到redis
                .reuseRefreshTokens(false) //refresh_token是否重复使用
                .userDetailsService(sysUserService) //刷新令牌授权包含对用户信息的检查
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST); //支持 GET,POST请求

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //允许表单认证
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
              //配置client_id
            .withClient("gateway")
              //配置client-secret
            .secret(passwordEncoder.encode("gateway"))
             //配置访问token的有效期
            .accessTokenValiditySeconds(3600)
            //配置刷新token的有效期
            .refreshTokenValiditySeconds(864000)
            //配置redirect_uri，用于授权成功后跳转
            .redirectUris("http://www.baidu.com")
            //配置申请的权限范围
            .scopes("all")
            //配置grant_type，表示授权类型
            //authorization_code，表示授权类型
            //implicit，简化模式
            //password，密码模式
            //client_credentials，客户端模式
            //refresh_token，更新令牌
            .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token");
    }
}
