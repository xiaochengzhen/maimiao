package com.example.springboot04;

import com.example.springboot04.model.User;
import com.example.springboot04.springEL.SpringEL;
import com.example.springboot04.springEL.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
/*
* Spring中#{}与${}的区别
#{}是SrpingEl表达式的语法规则.
例如:#{bean.属性?:默认值},注意bean.属性必须是要存在的,当为null时匹配
*
${}是Spring占位符的语法规则
请注意它是否能用,跟bean的初始化时间有关.
例如:${属性:默认值},如果属性为null或者不存在的话,就是用默认值填充
*
*
*
*
*
* */
@SpringBootTest
class Springboot04ApplicationTests {

    @Autowired
    private User user;
    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        System.out.println(user);
    }


    //=============================================================
    @Autowired
    private Student student;
    @Autowired
    private SpringEL springEl;

    @Test
    public void testEL1() {
        System.out.println(student.getName());
        System.out.println(springEl.getName());
    }

}
