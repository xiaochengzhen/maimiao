package com.example.springbootdymanic.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:26
 */
@Configuration
@MapperScan(basePackages = "com.example.springbootdymanic.mapper", sqlSessionFactoryRef="sqlSessionFactory2")
public class DatabaseConfig2 {

    @Bean
    @ConfigurationProperties("spring.datasource.datasource2")
    public DataSource dataSource2() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource2());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager2() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource2());
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate2() {
        return new TransactionTemplate(dataSourceTransactionManager2());
    }

}
