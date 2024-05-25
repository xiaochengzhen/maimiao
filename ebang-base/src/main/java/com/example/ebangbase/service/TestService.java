package com.example.ebangbase.service;

import com.example.ebangbase.mapper.TestMapper;
import com.example.ebangbase.model.TestDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/26 11:21
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    @Bean
    public ObjectMapper objectMapper1() {
        return new ObjectMapper();
    }

    public Integer saveTest1(TestDO testDO){
        testMapper.insert1(testDO);
        System.out.println("======================1============================="+testDO.getId());
        return testDO.getId();
    }
    public Integer saveTest2(TestDO testDO){
        testMapper.insert2(testDO);
        System.out.println("=========================2=========================="+testDO.getId());
        return testDO.getId();
    }

    public Integer saveTest3(TestDO testDO){
        testMapper.insert3(testDO);
        System.out.println("==========================3========================="+testDO.getId());
        return testDO.getId();
    }

    public List<TestDO> getTestDO() {
        List<TestDO> testDO = testMapper.getTestDO();
        return testDO;
    }

  //  @PostConstruct
    public void save() {
      Thread thread =   new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    TestDO testDO = new TestDO();
                    testDO.setAge(1L);
                    testDO.setName("1");
                   /* testDO.setLang("1");
                    testDO.setXxx("1");*/
                    if (i > 1) {
                        testDO.setAge((long) i);
                    }
                    testMapper.insert1(testDO);
                    System.out.println("=================="+i);
                  //  Thread.sleep(1000);
                    //    } catch (InterruptedException e) {
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
      thread.setDaemon(true);
      thread.start();
    }

}
