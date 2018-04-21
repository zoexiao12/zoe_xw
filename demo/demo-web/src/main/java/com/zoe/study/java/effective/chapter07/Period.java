package com.zoe.study.java.effective.chapter07;

import java.util.Date;

/**
 * Created by  on 2017/8/8.
 */
public class Period {
    /**
     * 虽然属性域定义为final，但仅仅属性域的引用不能变。而属性域本身却是可变的。
     * */
    private final Date start;
    private final Date end;

//    public Period(Date start, Date end) {
//        if(start.compareTo(end) > 0) {
//            throw new IllegalArgumentException(start + " after " + end);
//        }
//        this.start = start;
//        this.end = end;
//    }
//    public Date start() {
//        return start;
//    }
//    public Date end() {
//        return end;
//    }

    /**
     * 为保持类的不变，可对构造器的每个可变参数进行保护性拷贝（defensive copy）
     * 并且可使用备份对象作为类实例的组件，而非使用原始对象。
     * */
    public Period(Date start, Date end) {
        /**
         * 此处不用clone方法，因为clone方法不能保证返回一定为java.util.Date对象
         * 恶意程序可能会返回出于恶意目的而设计的java.util.Date的子类
         * */
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if(start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }
    //可变内部域的保护性拷贝
    public Date start() {
        return new Date(start.getTime());
    }
    public Date end() {
        return new Date(end.getTime());
    }


    public static  void main(String [] args) {
        Date start = new Date();
        Date end = new Date();
        Period  p = new Period(start,end);
        /**
         * 虽然值域定义为final，只是值域的引用不能变。
         * 但值域本身却是可变的。
         *  不做保护性copy的话 Period的end对象可变，也会违背end>start的约束
         * */
        end.setTime(12344);
        //通过方法修改值域对象
        p.end().setTime(3344444);
    }
}
