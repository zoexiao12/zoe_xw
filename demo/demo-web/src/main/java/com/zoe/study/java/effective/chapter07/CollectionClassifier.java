package com.zoe.study.java.effective.chapter07;

import java.util.*;

/**
 * Created by  on 2017/8/15.
 */
public class CollectionClassifier {

    /**
     * 以下为方法重载
     * */
    public String classify(Set<?> s) {
        return "Set";
    }
    public String classify(List<?> s) {
        return "List";
    }
//    public String classify(Collection<?> s) {
//        return "UnKnown Collection";
//    }
    /**
     * 替代方案
     * */
    public String classify(Collection<?> s) {
        return s instanceof  Set ? "Set" : s instanceof List ? "List" :"UnKnown Collection";
    }

    public static void main(String [] args) {
        CollectionClassifier cc = new CollectionClassifier();

        Collection<?> [] collections =  {
            new HashSet<String>(),
            new ArrayList<String>(),
            new HashMap<String,String>().values()
        };

        for (Collection c :collections) {
            /**
             * 调用哪个重载（overloading）方法是在编译时做出决定的。
             * 虽然运行时类型都不同，但不影响对重载方法的选择。
             * 因为该参数编译时类型为Collection<?>
             * 在循环的每次迭代中，都会调用这个重载方法
             * */
            System.out.println(cc.classify(c));
        }
    }

}
