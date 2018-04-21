package com.zoe.study.java.effective.chapter06;

/**
 * Created by  on 2017/8/23.
 */
public enum Planet {
    MERCURY(3.02e+23,2.439e6),
    VENUS(4.869E+24,6.052E6),
    EARTH(5.975E+24,6.378E6),
    MARS(6.419E+23,3.393E6),
    JUPITER(1.899E+27,7.149E7),
    SATURN(5.685e+26,6.027e7),
    URANUS(8.683e+25,2.556e7),
    NEPTUNE(1.024e+26,2.477e7);

    private final double mass;                  //质量
    private final double radius;                //半径
    private  final double surfaceGravity;      //表面重力 G * mass /(radius * radius)

    private static final double G = 6.67300E-11;

    /**
     * 构造函数
     * 为了将数据与枚举常量关联起来。
     *      声明实例域（都应该为final类型，尽量为private，提供共有的访问方法）
     *      并编写构造器（将数据保存到对应的域中）
     *
     */
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass /(radius * radius);
    }

    public double mass(){ return mass; }
    public double radius(){ return radius; }
    public double surfaceGravity() { return  surfaceGravity; }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }

}
