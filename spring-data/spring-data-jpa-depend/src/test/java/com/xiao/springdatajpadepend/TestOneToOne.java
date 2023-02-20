package com.xiao.springdatajpadepend;

import com.xiao.springdatajpadepend.config.SpringDataJpaConfig;
import com.xiao.springdatajpadepend.model.onetoone.Account;
import com.xiao.springdatajpadepend.model.onetoone.Customer;
import com.xiao.springdatajpadepend.service.onetoone.AccountRepository;
import com.xiao.springdatajpadepend.service.onetoone.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 15:01
 */
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestOneToOne {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void test1() {
        Optional<Customer> byId = customerRepository.findById(2);
        System.out.println(byId.get());
    }

    @Test
    public void test2() {
        Customer customer = new Customer();
        customer.setAge(1);
        customer.setName("a");
        Account account = new Account();
        account.setPassword("123456");
        account.setUsername("aaa");
        customer.setAccount(account);
        customerRepository.save(customer);
    }

    @Test
    public void test3() {
        customerRepository.deleteById(3);
    }
}
