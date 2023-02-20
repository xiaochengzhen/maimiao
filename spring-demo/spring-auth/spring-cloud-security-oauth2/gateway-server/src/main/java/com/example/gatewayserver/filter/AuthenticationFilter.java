package com.example.gatewayserver.filter;

import com.example.gatewayserver.common.MDA;
import com.example.gatewayserver.common.TokenInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 认证过滤器
 * @author Fox
 */
@Component
@Order(1)
public class AuthenticationFilter implements GlobalFilter, InitializingBean {

    @Autowired
    private RestTemplate restTemplate;

    private static Set<String> shouldSkipUrl = new LinkedHashSet<>();
    @Override
    public void afterPropertiesSet() throws Exception {
        // 不拦截认证的请求
        shouldSkipUrl.add("/auth-api/oauth/token");
        shouldSkipUrl.add("/auth-api/oauth/check_token");
        shouldSkipUrl.add("/auth-api/login");
        shouldSkipUrl.add("/auth-api/register");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getURI().getPath();
        if (requestPath.contains("oauth/token") || requestPath.contains("login")){
            String token = HttpHeaders.encodeBasicAuth(MDA.clientId, MDA.clientSecret, null);
            ServerHttpRequest request = exchange.getRequest().mutate().header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic "+token).build();
            return chain.filter(exchange.mutate().request(request).build());
        }
        //不需要认证的url
        if(shouldSkip(requestPath)) {
            return chain.filter(exchange);
        }
        //获取请求头
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        //请求头为空
        if(StringUtils.isEmpty(authHeader)) {
            throw new RuntimeException("请求头为空");
        }
        TokenInfo tokenInfo=null;
        try {
            //获取token信息
            tokenInfo = getTokenInfo(authHeader);
        }catch (Exception e) {
            throw new RuntimeException("校验令牌异常");
        }
        exchange.getAttributes().put("tokenInfo",tokenInfo);
        return chain.filter(exchange);
    }

    private boolean shouldSkip(String reqPath) {
        for(String skipPath:shouldSkipUrl) {
            if(reqPath.equals(skipPath)) {
                return true;
            }
        }
        return false;
    }

    private TokenInfo getTokenInfo(String authHeader) {
        // 获取token的值
        String token = StringUtils.substringAfter(authHeader, "Bearer ");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(MDA.clientId, MDA.clientSecret);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        ResponseEntity<TokenInfo> response = restTemplate.exchange(MDA.checkTokenUrl, HttpMethod.POST, entity, TokenInfo.class);
        return response.getBody();
    }
}
