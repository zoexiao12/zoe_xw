package com.zoe.study.java.java7.chapter10.finalize;

public class RunFinalize {
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("运行finalize方法");
		super.finalize();
	}

	public static void main(String[] args) throws Exception{
		@SuppressWarnings("unused")
		RunFinalize rf = new RunFinalize();
		rf = null;
		
		for(int i=0;i<100;i++){
			System.gc();
			Thread.sleep(100);
		}
	}

}
