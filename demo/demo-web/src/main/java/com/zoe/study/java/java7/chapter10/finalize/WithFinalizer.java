package com.zoe.study.java.java7.chapter10.finalize;

public class WithFinalizer {
	
	@SuppressWarnings("unused")
	private Object guardian = new Object(){
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
		}
	};

}
