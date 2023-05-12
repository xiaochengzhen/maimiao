package com.example.javabase.design.adapter.decorator;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 16:48
 */
public class Egg extends FryRice{
    private FryRice fryRice;

    public Egg(FryRice fryRice) {
        this.fryRice = fryRice;
    }

    @Override
    public String desc() {
        return fryRice.desc()+"加蛋";
    }

    @Override
    public int price() {
        return fryRice.price()+3;
    }
}
