package com.example.springannotionxml.service.impl;

import com.example.springannotionxml.model.Person;
import com.example.springannotionxml.service.PersonService;
import com.example.springannotionxml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/23 09:34
 */
@Service
public class PersonServiceImpl implements PersonService {

    /*
    * @Autowired 自动注入   先通过 byType 再通过 byName， 如果通过  byType 匹配多个 就用byName， 获取不到，就会报错
    * 处理方法：
    * 1、修改获取名字
    * 2、设置注入名字
    * 3、@Qualifier("userServiceImpl")，指定注入bean
    * 4、@Primary 标注优先选用
    * 5、泛型，通用service
    * */

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    public Person getPersion() {
        userService.getUser();
        System.out.println("=======success=======");
        return null;
    }
}
