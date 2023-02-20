package com.xiao.hibernate;

import com.xiaobo.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 10:56
 */
public class Test {
    SessionFactory sessionFactory;

    @Before
    public void init() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(build).buildMetadata();
        sessionFactory = metadata.buildSessionFactory();
    }

    @org.junit.Test
    public  void testC() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setId(4);
        user.setAge(1);
        user.setUserName("a");
        session.save(user);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    /*
    * HQL 不支持 insert into value （）  但是支持  insert into value  select from
    * */
    @org.junit.Test
    public  void testC_HQL() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setAge(1);
        user.setUserName("a");
        String sql = "insert into User (userName, age) select userName, age from User where id = 2 ";
        session.createQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @org.junit.Test
    public  void testR() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User load = session.load(User.class, 2);
        System.out.println(load);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @org.junit.Test
    public  void testR_HQL() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "from User where id=:id";
        User user = session.createQuery(sql, User.class).setParameter("id", 2).getSingleResult();
        System.out.println(user);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @org.junit.Test
    public  void testR_SQL() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from tbl_user where id=:id";
        User user = session.createNativeQuery(sql, User.class).setParameter("id", 2).getSingleResult();
        user.setUserName("ba1");//  此时  user 对象状态为持久化状态，对属性修改相当于对 数据库修改，当事务提交的时候就会生成一条update语句，也有可能没有，他会对比，如果没有修改，就不会生成update
        System.out.println(user);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @org.junit.Test
    public  void testD() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setId(2);
        session.delete(user);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
