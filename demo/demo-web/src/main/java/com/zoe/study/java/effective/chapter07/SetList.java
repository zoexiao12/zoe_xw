package com.zoe.study.java.effective.chapter07;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by  on 2017/8/15.
 */
public class SetList {
    public static void main(String [] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for (int i = -3; i <3; i++) {
            set.add(i);
            list.add(i);
        }
        for (int i = 0; i <3 ; i++) {
            /**
             * Set调用了重载方法remove(Object),此处Object为int ，会自动装箱到Integer。
             * 因此程序会从集合中删除[0，1，2]。剩下[-3,-2,-1]
             * */
            set.remove(i);
            /**
             * List调用了重载方法remove(int i),删除列表指定位置上的元素。
             * 其结果为[-2,0,2]
             * 为了正确的行为，可强制将参数转成为Integer，迫使选择重载方法remove(Object)。
             * */
//            list.remove(i);
            list.remove(Integer.valueOf(i));
        }
        System.out.println(set +"   -- "+ list);

    }
}
