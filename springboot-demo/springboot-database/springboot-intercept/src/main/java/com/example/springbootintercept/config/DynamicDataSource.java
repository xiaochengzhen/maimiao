package com.example.springbootintercept.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 11:24
 */
@Primary
@Configuration
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static ThreadLocal<String> name = new ThreadLocal<>();

    @Autowired
    private DataSource dataSource1;

    @Autowired
    private DataSource dataSource2;

    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();
    }

    @Override
    public void afterPropertiesSet()  {
        Map<Object, Object> map = new HashMap();
        map.put("datasource1", dataSource1);
        map.put("datasource2", dataSource2);
        super.setTargetDataSources(map);
        super.setDefaultTargetDataSource(dataSource1);
        super.afterPropertiesSet();
    }
}

