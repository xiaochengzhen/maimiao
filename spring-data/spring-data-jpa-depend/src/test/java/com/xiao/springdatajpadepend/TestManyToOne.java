package com.xiao.springdatajpadepend;

import com.xiao.springdatajpadepend.config.SpringDataJpaConfig;
import com.xiao.springdatajpadepend.model.manytoone.MessageManyToOne;
import com.xiao.springdatajpadepend.model.manytoone.PersonManyToOne;
import com.xiao.springdatajpadepend.service.manytoone.MessageRepository;
import com.xiao.springdatajpadepend.service.manytoone.PersonManyToOneRepository;
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
public class TestManyToOne {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PersonManyToOneRepository personManyToOneRepository;

    @Test
    public void test1() {
        Optional<MessageManyToOne> byId = messageRepository.findById(2);
        System.out.println(byId.get());
    }

    @Test
    public void test3() {
        Optional<PersonManyToOne> byId = personManyToOneRepository.findById(1);
        System.out.println(byId.get());
    }

    @Test
    public void test2() {
        PersonManyToOne person = new PersonManyToOne();
        person.setName("a");
        List<MessageManyToOne> list = new ArrayList<>();

        list.add(new MessageManyToOne("b", person));
        list.add(new MessageManyToOne("c", person));
        messageRepository.saveAll(list);
    }


}
