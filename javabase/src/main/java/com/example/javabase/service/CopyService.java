package com.example.javabase.service;

import org.junit.rules.Stopwatch;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyService implements InitializingBean {
    private List<CopyTest> list;

    @Override
    public void afterPropertiesSet() throws Exception {
        list = new ArrayList<>();
        CopyTest copyTest1 = new CopyTest();
        CopyTest copyTest2 = new CopyTest();
        list.add(copyTest1);
        list.add(copyTest2);
    }

    public void test() {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < 10000; i++) {
            List<CopyTest> copyList = new ArrayList<>(list);
        }
        stopwatch.stop();
        System.out.println(stopwatch.prettyPrint());
        //List<CopyTest> collect = list.stream().collect(Collectors.toList());


    }
    //1608500
    //494000
    //740200
    //3309700
    //5023200
    public void test1() {
        System.out.println(list.get(0).getName());
        System.out.println(list.size());
        List<CopyTest> copyList = new ArrayList<>(list);
        System.out.println(copyList.size());
        copyList.remove(1);
        System.out.println(copyList.size());
        CopyTest copyTest = copyList.get(0);
        copyTest.setName("1");
        System.out.println(list.get(0).getName());
        List<CopyTest> collect = list.stream().collect(Collectors.toList());
        collect.remove(1);
        System.out.println(collect.size());
        collect.get(0).setName("2");
        System.out.println(list.get(0).getName());


    }


}
