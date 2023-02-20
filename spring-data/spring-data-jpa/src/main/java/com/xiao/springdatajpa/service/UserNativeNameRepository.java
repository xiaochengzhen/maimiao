package com.xiao.springdatajpa.service;

import com.xiao.springdatajpa.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:16
 */
public interface UserNativeNameRepository extends CrudRepository<User,Integer>, QueryByExampleExecutor {

     //参数顺序要和name中顺序一致
     User findByAgeAndId(Integer age, Integer id);

     @Transactional
     @Modifying
     void deleteByIdAndAge(Integer id, Integer age);
}
