package com.xiao.springdatajpadepend.service.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;


/**
 * @author xiaobo
 * @description
 * @date 2022/12/8 16:53
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        // 动态注册了
        MyClassPathBeanDefinitionScanner scanner = new MyClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        // 限制必须实现Repository接口
        scanner.addIncludeFilter(new AssignableTypeFilter(Repository.class));
        scanner.scan("com.xiao.springdatajpadepend.service.onetoone");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
