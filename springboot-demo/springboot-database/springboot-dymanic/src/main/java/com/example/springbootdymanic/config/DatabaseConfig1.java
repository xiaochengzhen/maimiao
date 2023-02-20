package com.example.springbootdymanic.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:26
 */
@Configuration
@MapperScan(basePackages = "com.example.springbootdymanic.mapper", sqlSessionFactoryRef="sqlSessionFactory1")
public class DatabaseConfig1 {

    @Bean
    @ConfigurationProperties("spring.datasource.datasource1")
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean("sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource1());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager1() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource1());
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate1() {
        return new TransactionTemplate(dataSourceTransactionManager1());
    }

}
