package com.zoe.study.java.jvm.chapter02;

/**
 * VM Args : -Xss2M (这时候不妨设置大些)
 * @author zoe
 *
 */
public class JavaVMStackOOM {

	private void dontStop() {
		while(true) {}
	}

	public void stackLeakByThread() {
		while(true) {
			Thread t = new Thread(new Runnable(){
				@Override
				public void run() {
					dontStop();
				}
			});
			t.start();
		}
	}
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}

