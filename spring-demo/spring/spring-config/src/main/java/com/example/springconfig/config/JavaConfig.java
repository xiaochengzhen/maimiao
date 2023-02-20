package com.example.springconfig.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * java config 本来是spring的一个单独项目，spring3.0开始成为spring中，主要是再springboot中运用，完全摒弃了xml的配置，spring2.5开始虽然支持注解，但是不能脱离xml，因为注解
 * 不能解决第三方bean的注入和配置文件的读取，Javaconfig则解决了这个难题
 * springconfig 主要支持了  @Configuration  @Bean  @Import
 * @Configuration 表面了这个是一个配置类
 * @Bean 可以注入bean 第三方bean或者自身的都可以注入，他也替代了之前xml中factory-method 的构建工厂，自定义注入bean的方式，
 * @Impot 注解则非常主要 1、导入其他配置类； 2、导入未注入的bean； 3、导入ImportSelector的实现类，注入多个bean 4、导入ImportBeanDefinitionRegistrar的实现类，注入多个bean
 *
 * 将一个bean注入到ioc有几种
 *     1、xml <bean>
 *     2、@Component
 *     3、@Bean
 *     4、@Import
 *
 * @author xiaobo
 * @description
 * @date 2022/8/24 08:46
 */
@Configuration
@ComponentScan("com.example.springconfig")
@PropertySource("classpath:db.properties")
//@Import(Person.class) //注入的时候没有name，不能通过name获取
//@Import(JavaConfig2.class)
//@Import(MyImport.class)
@Import(MyImport2.class)
public class JavaConfig {

    @Value("${datasource.name}")
    private String name;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.driverClassName}")
    private String driverClassName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName(name);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
}
