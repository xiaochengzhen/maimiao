package com.xiao.springdatajpa.service;

import com.xiao.springdatajpa.model.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:16
 */
public interface UserQueryDSLRepository extends CrudRepository<User,Integer>, QuerydslPredicateExecutor<User> {
}
