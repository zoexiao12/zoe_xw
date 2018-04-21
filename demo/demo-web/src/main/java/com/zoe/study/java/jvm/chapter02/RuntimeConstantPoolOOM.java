package com.zoe.study.java.jvm.chapter02;
import java.util.ArrayList;
import java.util.List;

/**
 *  VM Args  :  -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {


		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern()== str1);
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern()==str2);

		//使用List保持常量池引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		int i = 0;
		while(true) {
			list.add(String.valueOf(i++).intern());
		}
	}

}







