package com.example.mybatisplusdemo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.example.mybatisplusdemo.selfinterceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/10 15:17
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    //    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
      //  interceptor.addInnerInterceptor(new MyInterceptor());
        return interceptor;
    }

}
