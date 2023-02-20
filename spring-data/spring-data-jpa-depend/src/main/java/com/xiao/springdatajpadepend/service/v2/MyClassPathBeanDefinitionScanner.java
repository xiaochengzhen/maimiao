package com.xiao.springdatajpadepend.service.v2;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;
import java.util.Set;

/**
 * @author xiaobo
 * @description
 * @date 2022/12/8 16:48
 */
public class MyClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder definitionHolder : beanDefinitionHolders) {

            ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition)definitionHolder.getBeanDefinition();

            String beanClass = beanDefinition.getBeanClassName();

            // 设置构造函数的值
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClass);

            // 偷天换日  mybatis
            beanDefinition.setBeanClass(MyFactoryBean.class);

        }
        return beanDefinitionHolders;
    }

    // 如果它是一个接口就视为有效组件 , 必须实现Repository
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return  metadata.isInterface();
    }

}
