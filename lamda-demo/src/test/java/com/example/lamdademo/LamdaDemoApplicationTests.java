package com.example.lamdademo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootTest
class LamdaDemoApplicationTests {

    public static String testSupplier(){
        return "Supplier";
    }

    public  String testFunction(){
        return "Supplier";
    }

    public  String testFunction1(){
        return "Supplier";
    }

    @Test
    public void test() {
        Supplier<String> supplier = LamdaDemoApplicationTests::testSupplier;
        String result = supplier.get();
        System.out.println(result);
    }

    @Test
    public void test2() {
        Function<LamdaDemoApplicationTests, String> function = LamdaDemoApplicationTests::testFunction;
        Function<LamdaDemoApplicationTests, String> function1 = LamdaDemoApplicationTests::testFunction1;

    }



    @Test
    public void test1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("衬衫的价格是" + aDouble);
            }
        });

        System.out.println("***************************");

        happyTime(400,money ->  System.out.println("衬衫的价格是" + money));

    }

    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }


}
