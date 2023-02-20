package com.xiao.springdatajpadepend.service.v2;

import com.xiao.springdatajpadepend.config.SpringDataJpaConfig;
import com.xiao.springdatajpadepend.model.onetoone.Customer;
import com.xiao.springdatajpadepend.service.onetoone.CustomerRepository;
import com.xiao.springdatajpadepend.service.proxy.MyJdkDynamicProxy;
import com.xiao.springdatajpadepend.service.proxy.MySimpleJpaRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.repository.Repository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/29 09:14
 */
public class MyStart2 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);
        CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
        Optional<Customer> byId = customerRepository.findById(1);
        System.out.println(byId.get());
    }



}
