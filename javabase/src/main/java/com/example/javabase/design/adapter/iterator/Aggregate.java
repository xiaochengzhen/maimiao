package com.example.javabase.design.adapter.iterator;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:35
 */
public interface Aggregate<T> {
    void add(T t);
    void remove(T t);
    Iterator<T> iterator();

}
