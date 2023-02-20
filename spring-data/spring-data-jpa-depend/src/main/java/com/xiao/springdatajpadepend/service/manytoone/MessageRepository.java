package com.xiao.springdatajpadepend.service.manytoone;

import com.xiao.springdatajpadepend.model.manytoone.MessageManyToOne;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 14:58
 */
public interface MessageRepository extends CrudRepository<MessageManyToOne, Integer> {
}
