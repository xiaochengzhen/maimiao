package com.example.springbootplus.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:26
 */
@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.datasource1")
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.datasource2")
    public DataSource dataSource2() {
        return DruidDataSourceBuilder.create().build();
    }


}
