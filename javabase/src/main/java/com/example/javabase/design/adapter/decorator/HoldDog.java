package com.example.javabase.design.adapter.decorator;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 16:48
 */
public class HoldDog extends FryRice{
    private FryRice fryRice;

    public HoldDog(FryRice fryRice) {
        this.fryRice = fryRice;
    }

    @Override
    public String desc() {
        return fryRice.desc()+"加热狗";
    }

    @Override
    public int price() {
        return fryRice.price()+5;
    }
}
