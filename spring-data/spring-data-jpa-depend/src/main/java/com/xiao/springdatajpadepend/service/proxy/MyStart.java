package com.xiao.springdatajpadepend.service.proxy;

import com.xiao.springdatajpadepend.config.SpringDataJpaConfig;
import com.xiao.springdatajpadepend.model.onetoone.Customer;
import com.xiao.springdatajpadepend.service.onetoone.CustomerRepository;
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
public class MyStart {


    public static void main(String[] args) {
        MyStart myStart = new MyStart();
        myStart.test();
    }

    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);
        CustomerRepository customerRepository = applicationContext.getBean("customerRepository", CustomerRepository.class);
        Optional<Customer> byId = customerRepository.findById(1);
        System.out.println(byId.get());
    }

    public void testproxy() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);
        LocalContainerEntityManagerFactoryBean bean = applicationContext.getBean(LocalContainerEntityManagerFactoryBean.class);
        EntityManagerFactory entityManagerFactory = bean.getNativeEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Class pojoClass = getPojoClass(CustomerRepository.class);
        CustomerRepository customerRepository = (CustomerRepository) Proxy.newProxyInstance(CustomerRepository.class.getClassLoader(),
                new Class[]{CustomerRepository.class}, new MyJdkDynamicProxy(new
                        MySimpleJpaRepository(entityManager, pojoClass)));
        Optional<Customer> byId = customerRepository.findById(1);
        System.out.println(byId.get());
    }

    public static Class getPojoClass(Class<? extends Repository> reposotory) {
        ParameterizedType genericInterface = (ParameterizedType) reposotory.getGenericInterfaces()[0];
        Type actualTypeArgument = genericInterface.getActualTypeArguments()[0];
        System.out.println(actualTypeArgument.getTypeName());
        try {
            return Class.forName(actualTypeArgument.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
