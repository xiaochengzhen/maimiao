package com.xiao.springdatajpadepend.service.onetoone;

import com.xiao.springdatajpadepend.model.onetoone.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/24 14:58
 */
@Component
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
}
