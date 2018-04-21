package com.zoe.study.java.java7.chapter1;

public class Title {
	public String generate(String name ,String gender) {
		String title = null;
		switch(gender) {
		case "男":
			title = name + " 先生";
			break;
		case "女" :
			title = name + " 女士";
			break;
		default :
			title = name;
		}
		return title;
	}
	public static void main(String[] args) {
		Title t = new Title();
		String title = t.generate("Roxy", "女");
		System.out.println(title);
	}

}
