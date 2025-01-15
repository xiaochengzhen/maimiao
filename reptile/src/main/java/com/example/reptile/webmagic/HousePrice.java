package com.example.reptile.webmagic;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/28 13:36
 */
@Data
public class HousePrice {

    private String name;
    private String priceStr;

    public static void main(String[] args) {
        Map<String, HousePrice> map = new ConcurrentHashMap<>();
        map.put(null, new HousePrice());
    }
}
