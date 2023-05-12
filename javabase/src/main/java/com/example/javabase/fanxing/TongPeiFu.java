package com.example.javabase.fanxing;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/17 09:25
 */
public class TongPeiFu <T>{

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        TongPeiFu<Integer> tongPeiFu1 = new TongPeiFu<>();
        tongPeiFu1.setT(1);
        TongPeiFu<String> tongPeiFu2 = new TongPeiFu<>();
        tongPeiFu2.setT("1");
        TongPeiFu<Double> tongPeiFu3 = new TongPeiFu<>();
        tongPeiFu3.setT(1.1);


        test(tongPeiFu1);
        test(tongPeiFu2);
        test(tongPeiFu3);

        test1(tongPeiFu1);
     //   test1(tongPeiFu2);
        test1(tongPeiFu3);

      //  test2(tongPeiFu1);
        test2(tongPeiFu2);
      //  test2(tongPeiFu3);
    }

    public static   void test11(TongPeiFu<?> tongPeiFu) {
        System.out.println(tongPeiFu.getT());
    }

    public static  <T> void test(TongPeiFu<T> tongPeiFu) {
        System.out.println(tongPeiFu.getT());
    }

    //参数是Number 或其子类
    public static void test1(TongPeiFu<? extends Number> tongPeiFu) {
       // tongPeiFu.setT("");
        System.out.println(tongPeiFu.getT());
    }

    //参数是Number 或其子类
    public static void test2(TongPeiFu<? super String> tongPeiFu) {
        tongPeiFu.setT("");
        System.out.println(tongPeiFu.getT());
    }
}
