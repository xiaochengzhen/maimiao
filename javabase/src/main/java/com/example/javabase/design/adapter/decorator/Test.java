package com.example.javabase.design.adapter.decorator;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 16:52
 */
public class Test {
    public static void main(String[] args) {
        FryRice fryRice = new FryRice();
        System.out.println(fryRice.desc()+"价格"+fryRice.price());
        Egg egg = new Egg(fryRice);
        System.out.println(egg.desc()+"价格"+egg.price());
        Egg egg2 = new Egg(egg);
        System.out.println(egg2.desc()+"价格"+egg2.price());
        HoldDog holdDog = new HoldDog(egg2);
        System.out.println(holdDog.desc()+"价格"+holdDog.price());

    }
}
