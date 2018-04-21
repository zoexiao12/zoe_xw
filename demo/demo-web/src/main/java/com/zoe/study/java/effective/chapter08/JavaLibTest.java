package com.zoe.study.java.effective.chapter08;


import java.util.Random;

/**
 * Created by  on 2017/8/17.
 */
public class JavaLibTest {

    private static final Random rnd = new Random();

    /**
     * 缺点：
     *  1.n为一个比较小的2的乘方，时间周期长时，产生的随机数将会重复很多。
     *  2.n不是2的乘方，平均起来，某些数会比其他的数出现更为频繁。
     *    n比较大，缺点会非常明显。2/3落在取值范围的前半部分。
     *  3.少数情况下，会返回一个落在指定范围之外的数。
     *    nextInt()可能返回Integer.MIN_VALUE，则Math.abs也会为Integer.MIN_VALUE。
     * @param n
     * @return
     */
    public int random(int n){
        return Math.abs(rnd.nextInt())% n;
    }

    public static void main(String [] args) {
        JavaLibTest jt = new JavaLibTest();

        int n = 2 * (Integer.MAX_VALUE /3);
        int low = 0;
        for (int i = 0; i <1000000 ; i++) {
            if(jt.random(n) < n/2){
                low++;
            }
        }
         System.out.println(low);
    }
}
