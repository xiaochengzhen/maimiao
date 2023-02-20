package com.example.authserver.config;


import com.example.authserver.model.TokenInfo;

public class UserContextHolder {

    private static final ThreadLocal<TokenInfo> USER_LOCAL = new ThreadLocal<>();

    public static void set(TokenInfo userTokenDTO) {
        USER_LOCAL.set(userTokenDTO);
    }

    public static TokenInfo getUser() {
        TokenInfo user = USER_LOCAL.get();
        return user;
    }

    public static void clear() {
        USER_LOCAL.remove();
    }
}
