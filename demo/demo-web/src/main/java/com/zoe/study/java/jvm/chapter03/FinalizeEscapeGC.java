package com.zoe.study.java.jvm.chapter03;

/**
 *  此代码演示了两点：
 *  1. 对象可以在GC的时自我拯救
 *  2. 这种自救机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次。
 * @author zoe
 *
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;




	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Finalize method executed！");
		SAVE_HOOK = this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();

		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500); //finalize方法优先级很低，所以暂停0.5毫秒以等待
		if(SAVE_HOOK != null) {
			System.out.println("yes, i am still  alive");
		}else {
			System.out.println("no, i am dead");
		}



		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK != null) {
			System.out.println("yes, i am still  alive");
		}else {
			System.out.println("no, i am dead");
		}


	}

}
