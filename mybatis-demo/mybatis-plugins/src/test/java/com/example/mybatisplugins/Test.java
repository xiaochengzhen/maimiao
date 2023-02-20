package com.example.mybatisplugins;

import com.example.mybatisplugins.mapper.UserMapper;
import com.example.mybatisplugins.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/16 10:59
 */
public class Test {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void before() {
        InputStream resourceAsStream = null;
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
    }

    @org.junit.Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.listUser();
        System.out.println(users);
    }

    /*
    * mybatis 的内存分页
    * */
    @org.junit.Test
    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        RowBounds rowBounds = new RowBounds(0, 2);
        List<User> users = mapper.listUser(rowBounds);
        System.out.println(users);
    }

    /*
     * pagehelper 分页
     * */
    @org.junit.Test
    public void test3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(1, 3);
        List<User> users = mapper.listUser();
        PageInfo pageInfo = new PageInfo(users);
        System.out.println(pageInfo);
    }


}
