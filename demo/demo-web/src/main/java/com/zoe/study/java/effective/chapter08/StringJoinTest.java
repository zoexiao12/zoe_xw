package com.zoe.study.java.effective.chapter08;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by  on 2017/8/18.
 */
public class StringJoinTest {

    public int numItems() {
        return 100000;
    }
    /**
     * 反复连接时，性能非常慢。
     */
    public String joinByString() {
        String result = "";
        for (int i = 0; i < numItems(); i++) {
            result += i;
        }
        return result;
    }
    /**
     * 100000个数字的连接，快了差不都4万倍
     */
    public String joinByStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numItems(); i++) {
            sb.append(i);
        }
        return sb.toString();
    }

    public static void main(String [] args) {
        StringJoinTest sj = new StringJoinTest();
        long start = System.currentTimeMillis();
        sj.joinByString();
        System.out.println(sj.numItems() + " 个数字的连接，用String实现耗时："
                + (System.currentTimeMillis()-start) );


        start = System.currentTimeMillis();
        sj.joinByStringBuilder();
        System.out.println(sj.numItems() + " 个数字的连接，用StringBuilder："
                + (System.currentTimeMillis()-start) );

    }
}
