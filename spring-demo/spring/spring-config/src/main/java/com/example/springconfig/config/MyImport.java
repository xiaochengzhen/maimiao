package com.example.springconfig.config;

import com.example.springconfig.model.Wife;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/24 09:57
 */
public class MyImport implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Wife.class.getName()};
    }
}
