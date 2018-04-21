package com.zoe.study.java.effective.chapter08;

import java.math.BigDecimal;

/**
 * Created by  on 2017/8/17.
 */
public class FloatDoubleTest {

    /**
     *
     */
    public void buySugarDouble(){
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = .10; funds > price; price += .10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("change : $"+funds);
    }

    /**
     *
     */
    public void buySugarInt(){
        int funds = 100;
        int itemsBought = 0;
        for (int price = 10; funds > price; price += 10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("change : $"+funds);
    }

    /**
     *
     */
    public void buySugarBigDecimal(){
        BigDecimal ten_centens = new BigDecimal(".10");
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.0");
        for(BigDecimal price =ten_centens; funds.compareTo(price) >= 0; price = price.add(ten_centens) ) {
            itemsBought++;
            funds = funds.subtract(price);
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("change : $"+funds);
    }

    public static void main(String [] args) {
        //输出结果并精确 0.6100000000000001
//        System.out.println(1.03 - 0.42);
        FloatDoubleTest t = new FloatDoubleTest();
        t.buySugarBigDecimal();


    }
}
