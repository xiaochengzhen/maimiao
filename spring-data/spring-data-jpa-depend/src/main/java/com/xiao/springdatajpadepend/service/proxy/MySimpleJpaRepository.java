package com.xiao.springdatajpadepend.service.proxy;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/29 09:33
 */
public class MySimpleJpaRepository implements CrudRepository {
    private EntityManager entityManager;
    private Class<?> clazz;

    public MySimpleJpaRepository() {
    }

    public MySimpleJpaRepository(EntityManager entityManager, Class<?> clazz) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public Iterable saveAll(Iterable entities) {
        return null;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.of(entityManager.find(clazz, o));
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteAllById(Iterable iterable) {

    }

    @Override
    public void deleteAll(Iterable entities) {

    }

    @Override
    public void deleteAll() {

    }
}
