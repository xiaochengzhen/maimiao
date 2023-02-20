package com.example.mybatisplusdemo.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author haig
 * @date 2022/4/11  11:23
 * @Description
 */
@Configuration
@MapperScan(basePackages = "com.example.mybatisplusdemo.mapper")
public class MPDataSoureConfig {

    private static final String MAPPER_LOCATION = "classpath:mapper/*.xml";
    @Bean(name = "mpSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mp")
    public DataSource dataSource () {
        return DataSourceBuilder.create().build();
    }

    /*@Bean(name = "mpSessionFactory")
    public SqlSessionFactory mpSessionFactory (@Qualifier("mpSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();

        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        //mybatis-plus全局配置设置元数据对象处理器为自己实现的那个
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        //mybatisSqlSessionFactoryBean关联设置全局配置
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                MPDataSoureConfig.MAPPER_LOCATION
        ));
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        mybatisSqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor);
        return mybatisSqlSessionFactoryBean.getObject();
    }*/
}
