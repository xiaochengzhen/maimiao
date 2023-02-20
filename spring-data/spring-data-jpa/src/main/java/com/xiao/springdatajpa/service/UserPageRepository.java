package com.xiao.springdatajpa.service;

import com.xiao.springdatajpa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:16
 */
public interface UserPageRepository extends PagingAndSortingRepository<User,Integer> {
}
