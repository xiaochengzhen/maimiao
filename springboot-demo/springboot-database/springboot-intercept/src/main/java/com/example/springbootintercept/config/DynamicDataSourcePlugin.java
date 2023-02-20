package com.example.springbootintercept.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

/**
 * @author xiaobo
 * @description
 * @date 2022/11/16 13:45
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
         @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,  ResultHandler.class})})
public class DynamicDataSourcePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatements = (MappedStatement) args[0];
        SqlCommandType sqlCommandType = mappedStatements.getSqlCommandType();
        if (sqlCommandType.equals(SqlCommandType.SELECT)) {
            DynamicDataSource.name.set("datasource1");
        } else {
            DynamicDataSource.name.set("datasource2");
        }
        return invocation.proceed();
    }
}
