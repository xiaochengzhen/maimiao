package com.xiao.springdatajpa.service;

import com.xiao.springdatajpa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author xiaobo
 * @description 动态条件
 * @date 2022/11/22 15:16
 */
public interface UserQueryByExampleRepository extends CrudRepository<User,Integer> , QueryByExampleExecutor<User> {
}
