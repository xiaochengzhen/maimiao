package com.example.javabase.design.adapter.iterator;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:31
 */
public interface Iterator<T> {
    Boolean hashNext();
    T next();
}
