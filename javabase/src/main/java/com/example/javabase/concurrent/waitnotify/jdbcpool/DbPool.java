package com.example.javabase.concurrent.waitnotify.jdbcpool;

import com.example.javabase.design.adapter.status.Context;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 11:12
 */
public class DbPool {

    private static LinkedList<Connection> pool = new LinkedList<>();

    public static void main(String[] args) {
        DbPool dbPool = new DbPool(20);
        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                Connection connection = dbPool.getConnection(1000);
                if (connection != null) {
                    System.out.println(Thread.currentThread().getName()+"拿到连接了");
                    Random random = new Random();
                    int i1 = random.nextInt(5);
                    try {
                        TimeUnit.SECONDS.sleep(i1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dbPool.releaseConnection(connection);
                    System.out.println(Thread.currentThread().getName()+"归还连接了");
                } else {
                    System.out.println(Thread.currentThread().getName()+"超时没有拿到连接");
                }
            }).start();
        }
    }
    public DbPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.add(new DbConnection());
            }
        }
    }

    public synchronized Connection getConnection(long mills) {
        Connection connection = null;
        if (mills < 0) {
            while (pool.size() <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            connection = pool.removeFirst();
        } else {
            long time = System.currentTimeMillis() + mills;
            long waitTime = mills;
            while (pool.size() <= 0 && waitTime > 0) {
                try {
                    wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitTime = time - System.currentTimeMillis();
            }
            if (pool.size() > 0) {
                connection = pool.removeFirst();
            }
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            pool.addLast(connection);
            notifyAll();
        }
    }

}
