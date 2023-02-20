package com.xiao.jpa;

import com.xiaobo.jpa.model.User;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 10:56
 */
public class Test {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernateJPA");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @org.junit.Test
    public  void testC() {
        String sql = "select u from User u";
        List<User> resultList = entityManager.createQuery(sql, User.class).getResultList();
        for (User user : resultList) {
            System.out.println(user);
        }
    }


}
