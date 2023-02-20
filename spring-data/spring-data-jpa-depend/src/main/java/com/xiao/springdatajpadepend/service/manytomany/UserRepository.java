package com.xiao.springdatajpadepend.service.manytomany;

import com.xiao.springdatajpadepend.model.manytomany.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/29 08:55
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
