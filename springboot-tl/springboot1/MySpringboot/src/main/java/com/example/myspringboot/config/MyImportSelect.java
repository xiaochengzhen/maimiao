package com.example.myspringboot.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Predicate;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/13 11:17
 */
public class MyImportSelect implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 自动配置
        ServiceLoader<AutoConfiguration> loader = ServiceLoader.load(AutoConfiguration.class);
        List<String> list = new ArrayList<>();
        for (AutoConfiguration configuration : loader) {
            list.add(configuration.getClass().getName());
        }
        return list.toArray(new String[0]);
    }


    @Override
    public Predicate<String> getExclusionFilter() {
        return DeferredImportSelector.super.getExclusionFilter();
    }


    @Override
    public Class<? extends Group> getImportGroup() {
        return DeferredImportSelector.super.getImportGroup();
    }
}
