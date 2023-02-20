package com.example.springbootdymanic.service.impl;

import com.example.springbootdymanic.mapper.UserMapper;
import com.example.springbootdymanic.model.User;
import com.example.springbootdymanic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 10:16
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }

    @Autowired
    private TransactionTemplate transactionTemplate1;
    @Autowired
    private TransactionTemplate transactionTemplate2;

    /*
    * 多数据源事务处理
    * */
    public void testTran() {
        transactionTemplate1.execute(status1 -> {
            transactionTemplate2.execute(status2 -> {
                try {
                   // saveUser1();
                 //   saveUser2();
                } catch (Exception e) {
                    status1.setRollbackOnly();
                    status2.setRollbackOnly();
                    return false;
                }
                return true;
            });
            return true;
        });
    }

    /*
     * 多数据源事务处理2
     * */
    @Transactional(transactionManager="dataSourceTransactionManager1")
    public void testTran2() {
        testTran1();
    }
    @Transactional(transactionManager="dataSourceTransactionManager2")
    public void testTran1() {
        // saveUser1();
        // saveUser2();
    }
}
