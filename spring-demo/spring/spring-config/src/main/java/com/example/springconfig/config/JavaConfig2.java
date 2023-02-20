package com.example.springconfig.config;


import com.example.springconfig.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * java config 本来是spring的一个单独项目，spring3.0开始成为spring中，主要是再springboot中运用，完全摒弃了xml的配置，spring2.5开始虽然支持注解，但是不能脱离xml，因为注解
 * 不能解决第三方bean的注入和配置文件的读取，Javaconfig则解决了这个难题
 * springconfig 主要支持了  @Configuration  @Bean  @Import
 * @Configuration 表面了这个是一个配置类
 * @Bean 可以注入bean 第三方bean或者自身的都可以注入，他也替代了之前xml中factory-method 的构建工厂，自定义注入bean的方式，
 * @Impot 注解则非常主要
 *
 * @author xiaobo
 * @description
 * @date 2022/8/24 08:46
 */
public class JavaConfig2 {

   @Bean
   public Role role() {
      Role role = new Role();
      return role;
   }
}
