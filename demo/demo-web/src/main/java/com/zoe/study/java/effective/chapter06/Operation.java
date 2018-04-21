package com.zoe.study.java.effective.chapter06;

import org.apache.hadoop.hdfs.server.namenode.top.window.RollingWindowManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 2017/8/23.
 */
public enum Operation {
//    PLUS,MINUS,TIMES,DIVIDE,DPLUS;

    PLUS("+"){
        double apply(double x, double y) {return x + y; }
    },
    MINUS("-"){
        double apply(double x, double y) {return x - y; }
    },
    TIMES("*"){
        double apply(double x, double y) {return x * y; }
    },
    DIVIDE("/"){
        double apply(double x, double y) {return x / y; }
    },
    DPLUS("++ +"){
        double apply(double x, double y) {return ++x + ++y; }
    };

    /**
     * 定义抽象方法将不同的行为与每个枚举常量关联起来。
     * 在特定的常量的类主题中，用具体的方法覆盖枚举类中定义的抽象方法。
     * 定义每个常量声明之后，必须提供对应的抽象方法实现，否则编译将无法通过。
     */
    abstract double apply(double x, double y);

    /**
     * 构造时候给定描叙，然后覆盖toString方法。
     */
    private  final  String symbol;
    private static final Map<String,Operation> StringToEnum = new HashMap<>();
    /**
     * 枚举静态域，采用静态块初始化。不能早构造函数中初始化
     */
    static {
        for (Operation o : Operation.values()) {
            StringToEnum.put(o.toString(),o);
        }
    }

    //构造器
    Operation(String symbol) {
        this.symbol = symbol;
        /**
         * 此处会报错
         * 枚举构造器不可访问枚举静态域。因为构造器运行时，这些静态域还没有被初始化。
         */
//        for (Operation o : Operation.values()) {
//            StringToEnum.put(o.toString(),o);
//        }
    }

    /**
     * 将定制的字符串表示法变回相应的枚举。
     * 前提：需要每个常量都有个独特的字符串表示法。
     */
    public static Operation fromString(String symbol) {
        return StringToEnum.get(symbol);
    }
    @Override
    public String toString() {
        return symbol;
    }

    /**
     * 枚举中的swith语句适合给外部的枚举类型增加特定于常量的行为。
     * 返回每个运算的反运算。
     */
    public static Operation inverse(Operation o) {
        switch (o) {
            case PLUS:    return Operation.MINUS;
            case MINUS:   return Operation.PLUS;
            case TIMES:   return Operation.DIVIDE;
            case DIVIDE:  return Operation.TIMES;
            default: throw new AssertionError("unkown Operation: " + o);
        }
    }

//    /**
//     * 问题：
//     *  1 没有throw语句，编译将不会通过。
//     *  2 程序脆弱，添加了新枚举常量，忘记switch添加相应的条件，可编译，但运行新运算时，就会失败。
//     */
//    double apply(double x, double y) {
//        switch (this) {
//            case PLUS:    return x + y;
//            case MINUS:   return x - y;
//            case TIMES:   return x * y;
//            case DIVIDE:  return x / y;
//        }
//        throw new AssertionError("unknow operation : " + this);
//    }




//    @Override
//    public String toString() {
//        switch (this) {
//            case PLUS:  return  "加";
//            case MINUS: return "减";
//            case TIMES: return "乘";
//            case DIVIDE: return "除";
//        }
//        throw new AssertionError("unknow operation : " + this);
//    }
}
