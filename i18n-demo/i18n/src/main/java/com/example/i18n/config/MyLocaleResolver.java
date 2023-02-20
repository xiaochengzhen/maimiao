package com.example.i18n.config;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/14 14:39
 */
/*public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取请求中的语言参数
        String language = httpServletRequest.getParameter("lang");
        System.out.println("DeBug===>"+language);
        Locale locale=Locale.getDefault(); //如果没有就使用默认的（根据主机的语言环境生成一个 Locale ）。
        //如果请求的链接中携带了 国际化的参数
        if (!StringUtils.isEmpty(language)){
            //zh_CN
            String[] s = language.split("_");
            //国家，地区
            locale=new Locale(s[0],s[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {


    }
}*/
