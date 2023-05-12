package com.example.javabase;

/**
 * @description
 * @author xiaobo
 * @date 2023/4/23 09:56
 */

import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.math.BigDecimal;

/**
 * @description
 * @author xiaobo
 * @date 2023/4/23 9:56
 */
public class TopTest {
    public static void main(String[] args) {
        BigDecimal b = new BigDecimal(0);
        test();
        System.out.println(b.stripTrailingZeros());
        System.out.println(b.stripTrailingZeros().toPlainString());
    }

    public static void test() {
        System.out.println("===========1=======");
        System.out.println("===========2=======");
    }
}
