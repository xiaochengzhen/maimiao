package com.xiao.springdatajpadepend.service.onetomany;

import com.xiao.springdatajpadepend.model.onetomany.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 14:58
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
