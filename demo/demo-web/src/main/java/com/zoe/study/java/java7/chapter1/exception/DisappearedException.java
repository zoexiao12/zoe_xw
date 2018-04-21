package com.zoe.study.java.java7.chapter1.exception;

public class DisappearedException {
	
	public void show() throws BaseException {
		try{
			Integer.parseInt("Hello");
		}catch(NumberFormatException e) {  //此异常被忽略；
			throw new BaseException (e);
		}finally {
			try{
				@SuppressWarnings("unused")
				int result = 2/0;
			}catch(ArithmeticException ae) {
				throw new BaseException (ae);
			}
		}
	}

	public static void main(String[] args) throws BaseException {
		DisappearedException de = new DisappearedException();
		de.show();
	}

}
