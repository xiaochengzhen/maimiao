package com.example.myspringboot.config;

import com.example.myspringboot.annotion.MyCondtionalOnClass;
import com.example.myspringboot.service.impl.JettyWebServer;
import com.example.myspringboot.service.impl.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/13 9:01
 */
@Configuration
public class WebServiceAutoConfiguration implements AutoConfiguration {

    @Bean
    @MyCondtionalOnClass(value = "org.apache.catalina.startup.Tomcat")
    public TomcatWebServer tomcatWebServer() {
        return new TomcatWebServer();
    }

    @Bean
    @MyCondtionalOnClass(value = "org.eclipse.jetty.server.Server")
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }

}
