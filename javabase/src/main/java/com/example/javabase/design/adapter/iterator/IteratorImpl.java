package com.example.javabase.design.adapter.iterator;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:32
 */
public class IteratorImpl<T> implements Iterator<T>{
    private List<T> list;
    private T element;
    private int cur;

    public IteratorImpl(List<T> list) {
        this.list = list;
    }

    @Override
    public Boolean hashNext() {
        return cur<list.size();
    }

    @Override
    public T next() {
        element = list.get(cur);
        cur++;
        return element;
    }
}
