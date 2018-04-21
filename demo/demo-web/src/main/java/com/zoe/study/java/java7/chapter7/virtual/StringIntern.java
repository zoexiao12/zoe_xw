package com.zoe.study.java.java7.chapter7.virtual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringIntern {
	
	private List<String> list = new ArrayList<>();
	
	public void useInternString() {
		Random rd = new Random();
		
		for(int i=0;i<5000;i++) {
			char [] data = new char[128*1024];
			for(int j=0;j<data.length;j++) {
				data[j] = (char)rd.nextInt(32769);
			}
			list.add(new String(data).intern());
		}
	}

	public static void main(String[] args) {
		StringIntern si = new StringIntern();
		si.useInternString();
		for(String t : si.list) {
			System.out.println(t);
		}
	}

}
