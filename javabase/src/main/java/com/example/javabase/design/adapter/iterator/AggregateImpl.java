package com.example.javabase.design.adapter.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 16:36
 */
public class AggregateImpl<T> implements Aggregate<T> {
    private List<T> list = new ArrayList<>();

    @Override
    public void add(T t) {
        list.add(t);
    }

    @Override
    public void remove(T t) {
        list.remove(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl<>(list);
    }

    public static void main(String[] args) {
        Aggregate aggregate = new AggregateImpl();
        aggregate.add(1);
        aggregate.add(2);
        Iterator iterator = aggregate.iterator();
        while (iterator.hashNext()) {
            System.out.println(iterator.next());
        }
    }
}
