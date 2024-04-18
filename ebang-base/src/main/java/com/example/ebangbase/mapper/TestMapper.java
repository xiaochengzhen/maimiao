package com.example.ebangbase.mapper;

import com.example.ebangbase.model.TestDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface TestMapper {

    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert("insert into test(age, name) values(#{age}, #{name})")
    int insert1(TestDO testDO);

    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert("insert into test(id, age, name) values(#{id}, #{age}, #{name})")
    int insert2(TestDO testDO);

    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert({"insert into test(id, age, name) values(#{id}, #{age}, #{name})"})
    int insert3(TestDO testDO);

    @Select("select id id, age age from test")
    List<TestDO> getTestDO();

   /* @Select("select * from test where id >= 5")
    TestDO selectTestDO1();

    @Select("select * from test where id <= 5")
    List<TestDO> getTestDO();

    @Select({"<script>" ,
            "select * from test where id >= 5",
    "</script>"})
    TestDO selectTestDO3();*/

    @Select(
            "select * from test where id < 5"
            )
    TestDO selectTestDO4();

}
