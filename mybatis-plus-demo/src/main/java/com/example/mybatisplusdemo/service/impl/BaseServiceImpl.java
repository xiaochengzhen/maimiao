package com.example.mybatisplusdemo.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplusdemo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/10 10:58
 */
@Service
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl implements BaseService<T> {

    @Autowired
    private M baseDao;

    @Override
    public void ext(T t) {
        baseDao.insert(t);
    }
}
