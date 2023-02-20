package com.xiao.springdatajpadepend;

import com.xiao.springdatajpadepend.config.SpringDataJpaConfig;
import com.xiao.springdatajpadepend.model.onetomany.Message;
import com.xiao.springdatajpadepend.model.onetomany.Person;
import com.xiao.springdatajpadepend.service.onetomany.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 15:01
 */
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestOneToMany {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test1() {
        Optional<Person> byId = personRepository.findById(2);
        System.out.println(byId.get());
    }

    @Test
    public void test2() {
        Person person = new Person();
        person.setName("a");
        List<Message> list = new ArrayList<>();
        list.add(new Message("b"));
        list.add(new Message("c"));
        person.setList(list);
        Person save = personRepository.save(person);
    }


}
