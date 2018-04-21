package com.zoe.study.java;

/**
 * Created by zoe on 2017/11/8.
 */
public class Test {

    public  static  void main(String [] args) {

        //eg:"select '123\\;456; select 1;" split to:
        // select '123\\;456
        // select 1
        //.$|()[{^?*+\\  a(?!b)
        String xx = "select '123\\\\;456;select 1;";
       String [] ar =  xx.split("(?<!\\\\);");
        for (String t : ar) {
            System.out.println(t);
        }

    }
}
