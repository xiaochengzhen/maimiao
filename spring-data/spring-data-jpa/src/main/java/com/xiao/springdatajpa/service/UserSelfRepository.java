package com.xiao.springdatajpa.service;

import com.xiao.springdatajpa.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/22 15:16
 */
public interface UserSelfRepository extends CrudRepository<User,Integer> {

    @Query("select u from User u")
    List<User> listUser();

    @Query("select u from User u where u.id=:id")
    User getUserById(@Param("id") Integer id);

    @Query(value = "select * from tbl_user  where id=:id", nativeQuery = true)
    User getUserByIdNative(@Param("id") Integer id);

    /*
    * 操作数据必须加上  @Transactional  @Modifying
    * */
    @Transactional
    @Modifying
    @Query("update User u set u.age =:age where u.id=:id")
    void updateUser(@Param("id") Integer id, @Param("age") Integer age);
}
