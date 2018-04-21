package com.zoe.study.java.effective.chapter08;

import java.util.Comparator;

/**
 * Created by  on 2017/8/18.
 */
public class BoxedPrimitiveTest {

    public static void main(String [] args) {
        Comparator<Integer> c = new Comparator<Integer>(){
            @Override
            public int compare(Integer first, Integer second) {
                /**
                 * 错误的实现
                 * first < second  表达式的执行，会将first，second的Integer实例自动拆箱。 这步ok
                 * 但如果上一步是false，则会继续执行表达式first == second，此处会两个对象上执行同一性比较。
                 * 如果first和second是值相同的不同对象。first == second表达式false，其比较结果为1.
                 * 则可能与预期不符合。
                 * */
//                return first < second  ? -1 :(first == second ? 0 : 1);

                /**
                 * 正确做法
                 * 增加两个局部变量，将装箱基本类型转换为基本类型。
                 * 对基本类型进行比较，则避免同一性比较出现的错误结果。
                 */
                int f = first;
                int s = second;
                return  f < s ? -1 : (f == s ? 0 : 1);
            }
        };

        System.out.println(c.compare(new Integer(42),new Integer(42)));
    }
}
