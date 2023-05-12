package com.example.javabase.concurrent.waitnotify.jdbcpool;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 11:16
 */
public class TestDbPool {
    public static void main(String[] args) {
        DbPool dbPool = new DbPool(20);
    }
}
