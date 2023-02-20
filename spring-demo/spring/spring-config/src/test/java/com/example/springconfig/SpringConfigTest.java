package com.example.springconfig;


import com.example.springconfig.config.JavaConfig;
import com.example.springconfig.model.Person;
import com.example.springconfig.model.Role;
import com.example.springconfig.model.User;
import com.example.springconfig.model.Wife;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/24 08:50
 */
public class SpringConfigTest {
    ApplicationContext applicationContext;
    @Before
    public void before() {
        applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
    }

    /*
    *测试不需要xml
    * */
    @Test
    public  void test1() {
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user.getName());
    }

    /*
    * 测试注入第三方bean
    * */
    @Test
    public  void test2() {
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        System.out.println(dataSource);
    }

    /*
    * 测试import注入bean
    * */
    @Test
    public  void test3() {
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
    }

    /*
     * 测试import注入配置文件
     * */
    @Test
    public void test4() {
        Role bean = applicationContext.getBean(Role.class);
        System.out.println(bean);
    }

    /*
     * 测试import注入没有被扫描的bean，根据全路径名，可以是外部bean  implements ImportSelector
     * */
    @Test
    public void test5() {
        Wife bean =   applicationContext.getBean(Wife.class);
        System.out.println(bean);
    }

    /*
     * 测试import注入没有被扫描的bean，根据全路径名，可以是外部bean  implements ImportBeanDefinitionRegistrar
     * */
    @Test
    public void test6() {
        Wife bean = (Wife) applicationContext.getBean("wife");
        System.out.println(bean.getName());
    }
}
