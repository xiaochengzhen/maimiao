package com.example.javabase.design.adapter.adapterObject;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 15:42
 */
public class PowerAdapter implements Dc5{
    private Power power;

    public PowerAdapter(Power power) {
        this.power = power;
    }

    @Override
    public int out5() {
        int out = power.out();
        return out/44;
    }

    public static void main(String[] args) {
        PowerAdapter powerAdapter =  new PowerAdapter(new Ac220());
        int i = powerAdapter.out5();
        System.out.println(i);
    }
}
