package com.example.mybatisplusdemo.service.impl;

import com.example.mybatisplusdemo.mapper.TableUserMapper;
import com.example.mybatisplusdemo.mapper.UserDao;
import com.example.mybatisplusdemo.model.TableUser;
import com.example.mybatisplusdemo.model.User;
import com.example.mybatisplusdemo.service.TableUserService;
import com.example.mybatisplusdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/7 17:34
 */
@Service
public class TableUserServiceImpl extends BaseServiceImpl<TableUserMapper, TableUser> implements TableUserService {
    @Resource
    private TableUserMapper tableUserMapper;

    @Lazy
    @Autowired
    private  TableUserServiceImpl tableUserServiceImpl;
    @Transactional
    public void testTran() {
        TableUser tableUser = new TableUser();
        tableUser.setAge(1);
        tableUserMapper.insertTableUser(tableUser);
        try {

            tableUserServiceImpl.testTran1();
        } catch (Exception e) {

        }
    }

  // @Transactional
    public void testTran1() {
        TableUser tableUser = new TableUser();
        tableUser.setAge(2);
        tableUserMapper.insertTableUser(tableUser);
        throw new NullPointerException();
    }



}
