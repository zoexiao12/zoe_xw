package com.zoe.study.java.java7.chapter1;

public class TitleDuplicate {
	public String generate(String name ,String gender) {
		String title = null;
		switch(gender) {
		case "男":
			title = name + " 先生";
			break;
//转义成Unicode字符后，于"男"重复	
//		case "\u7537":
//			title = name + " 先生";
//			break;	
		case "女" :
			title = name + " 女士";
			break;
		default :
			title = name;
		}
		return title;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
