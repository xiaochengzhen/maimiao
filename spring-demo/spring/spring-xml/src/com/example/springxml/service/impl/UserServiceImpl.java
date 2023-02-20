package com.example.springxml.service.impl;

import com.example.springxml.model.Student;
import com.example.springxml.service.UserDao;
import com.example.springxml.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/10 10:17
 */
public class UserServiceImpl implements UserService {

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

      private UserDao userDao;


    @Override
    public void getUserMessage() {
        userDao.getUser();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("web.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        Student bean = applicationContext.getBean(Student.class);
        userService.getUserMessage();
    }
}
