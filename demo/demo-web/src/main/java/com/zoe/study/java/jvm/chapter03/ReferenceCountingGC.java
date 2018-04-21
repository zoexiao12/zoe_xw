package com.zoe.study.java.jvm.chapter03;
/**
 * testGC() 方法执行后，objA和objB会不会被GC？
 * @author zoe
 *
 */
public class ReferenceCountingGC {

	public Object instance = null;

	private static final int _1MB = 1024 * 1024;

	/**
	 * 此成员的意义在于，占用点内存，以便在GC日志中看清楚是否被回收过。
	 */
	@SuppressWarnings("unused")
	private byte [] bigSize = new byte [2 * _1MB];

	public static void testGC() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;

		objA = null;
		objB = null;

		//假设这行发生GC，objA和objB是否能被回收？
		System.gc();

	}

	public static void main(String[] args) {
		testGC();
	}

}
