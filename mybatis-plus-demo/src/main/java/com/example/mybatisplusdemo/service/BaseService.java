package com.example.mybatisplusdemo.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/10 10:47
 */
public interface BaseService<T> extends IService {

    void ext(T t);
}
