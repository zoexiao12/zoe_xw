package com.zoe.study.java.java7.chapter2;

import java.util.Arrays;
import java.util.List;
//import java.util.function.Consumer;

public class LambdaTest {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		//函数式接口，单一抽象single abstract method
//		list.forEach(new Consumer(){
//			@Override
//			public void accept(Object t) {
//				System.out.println(t);
//				
//			}
//			
//		});
		list.forEach(el->System.out.println(el));
		
	}

}
