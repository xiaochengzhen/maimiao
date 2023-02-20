package com.xiao.springdatajpadepend.service.onetoone;

import com.xiao.springdatajpadepend.model.onetoone.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 14:59
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {
}
