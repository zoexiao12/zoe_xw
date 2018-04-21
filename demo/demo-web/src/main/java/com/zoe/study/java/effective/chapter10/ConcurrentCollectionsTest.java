package com.zoe.study.java.effective.chapter10;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by  on 2017/8/7.
 */
public class ConcurrentCollectionsTest {

    private ConcurrentMap<String,String> map = new ConcurrentHashMap<>();

//    public  String intern(String s) {
//        String previousValue = map.putIfAbsent(s,s);
//        return previousValue==null ? s : previousValue;
//    }
    public  String intern(String s) {
        String result = map.get(s);
        if(result == null) {
            result = map.putIfAbsent(s,s);
            if(result == null) {
                result = s;
            }
        }
        return result;
    }

    public static void main(String [] args) {

    }
}
