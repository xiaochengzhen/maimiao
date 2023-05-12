package com.example.javabase.design.adapter.adapterclass;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 13:25
 */
public class PowerAdapter extends Ac220 implements Dc5{
    @Override
    public int out5() {
        int i = out220();
        return i/44;
    }

    public static void main(String[] args) {
        PowerAdapter powerAdapter = new PowerAdapter();
        int i = powerAdapter.out220();
        System.out.println(i);
        int i1 = powerAdapter.out5();
        System.out.println(i1);
    }
}
