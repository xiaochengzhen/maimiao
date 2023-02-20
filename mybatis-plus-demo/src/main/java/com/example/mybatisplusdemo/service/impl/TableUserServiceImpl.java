package com.example.mybatisplusdemo.service.impl;

import com.example.mybatisplusdemo.mapper.TableUserMapper;
import com.example.mybatisplusdemo.mapper.UserDao;
import com.example.mybatisplusdemo.model.TableUser;
import com.example.mybatisplusdemo.model.User;
import com.example.mybatisplusdemo.service.TableUserService;
import com.example.mybatisplusdemo.service.UserService;
import org.springframework.stereotype.Service;

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



}
