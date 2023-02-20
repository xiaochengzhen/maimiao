package com.example.springannotionxml;

import com.example.springannotionxml.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotionXmlApplicationTests {
    ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:web.xml");
    }

    @Test
    public void test1() {
        PersonService personService = applicationContext.getBean("personServiceImpl", PersonService.class);
        personService.getPersion();
    }

}
