package com.xiao.springdatajpadepend.service.v2;

import com.xiao.springdatajpadepend.service.onetoone.CustomerRepository;
import com.xiao.springdatajpadepend.service.proxy.MyJdkDynamicProxy;
import com.xiao.springdatajpadepend.service.proxy.MySimpleJpaRepository;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * @author xiaobo
 * @description
 * @date 2022/12/8 16:59
 */
public class MyFactoryBean implements FactoryBean {
    @Autowired
    LocalContainerEntityManagerFactoryBean entityManagerFactory;

    // 怎么赋值？？
    Class<?> repositoryInterface;

    public MyFactoryBean(Class<?> repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }

    // 随意控制实例化过程
    @Override
    public Object getObject() throws Exception {
        //   获得EntityManager
        EntityManager em = entityManagerFactory.createNativeEntityManager(null);

        //  获得当前接口的pojo类
        // getGenericInterfaces() 拿到当前接口的父接口 = PagingAndSortingRepository
        ParameterizedType parameterizedType =(ParameterizedType) repositoryInterface.getGenericInterfaces()[0];
        // 能拿到接口的泛型 <Customer,Long>
        Type type = parameterizedType.getActualTypeArguments()[0];
        Class clazz=Class.forName(type.getTypeName());

        return Proxy.newProxyInstance(repositoryInterface.getClassLoader(),
                new Class[]{repositoryInterface}, new MyJdkDynamicProxy(new
                        MySimpleJpaRepository(em, clazz)));

    }

    // getObject返回对象的类型
    @Override
    public Class<?> getObjectType() {
        return repositoryInterface;
    }
}
