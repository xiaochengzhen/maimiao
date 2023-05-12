package com.example.javabase.inner;

import com.example.javabase.reflex.Test;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/15 17:27
 */
@Data
public class TestInner {
    private Integer age;
    private static String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        TestInner.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public TestInner() {
        System.out.println();
        List<TestInner> list = new ArrayList<>();
        list.stream().map(TestInner::getAge);
        list.stream().map(s->s.getAge());
    }

    public TestInner(Integer age, long length) {
        this.age = age;
        this.length = length;
    }

    public TestInner(long length, Integer age) {
        this.length = length;
        this.age = age;
    }

    public void testLambda() {
           int a = 1;
       /* new Thread(()->{
           a++;
        });*/
           Function<Integer, Integer> f = i->i+a;
        Function<Integer, Integer> f1 = i->{
            return a;
        };
    }

    public long length;

    public void testI() {
        Inner inner = new Inner();
    }
    @Data
    public class Inner{
        public void test() {
            String s = name;
            name = name+"a";
            int i = age==null?0:age;
            age = 2;
            System.out.println(age);
        }
    }
    @Data
    public static class InnerStatic{
        public void test() {
            String s = name;
           // int i = age;
        }
    }
          Integer a = 1;
    public void testLambda1() {
        new Thread(()->{
            a= a+1;
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Function<Integer, Integer> function = i -> {
            a++;
            return a;
        };
        Integer apply = function.apply(2);
        System.out.println("===="+a);
    }

    public static void main(String[] args) {
       /* TestInner testInner = new TestInner();
        System.out.println(testInner.age);
        Inner inner = new TestInner().new Inner();
        inner.test();
        TestInner testInner1 = new TestInner();
        System.out.println(testInner1.age);
       // Inner inner1 = new Inner();
        InnerStatic innerStatic = new InnerStatic();

        InnerStatic innerStatic1 = new TestInner.InnerStatic();*/

        TestInner testInner = new TestInner();
        System.out.println("++"+testInner.a);
        testInner.testLambda1();
        System.out.println("+++"+testInner.a);
    }
}
