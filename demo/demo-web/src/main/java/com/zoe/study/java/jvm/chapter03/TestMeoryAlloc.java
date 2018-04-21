package com.zoe.study.java.jvm.chapter03;


public class TestMeoryAlloc {

	private static final int _1MB = 1024 * 1024;


	/**
	 * VM参数：-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 */
	@SuppressWarnings("unused")
	public static void testEdenAlloc() {
		byte [] a1,a2,a3,a4;
		a1 = new byte[2 *_1MB ];
		a2 = new byte[2 *_1MB ];
		a3 = new byte[2 *_1MB ];
		a4 = new byte[3 *_1MB ];  //发生一次Minor GC
	}

	/**
	 * VM参数：-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=2097152
	 *5242880
	 *PretenureSizeThreshold Paralle Scavenge收集器下无效
	 */
	@SuppressWarnings("unused")
	public static void testBigDataAlloc() {
		byte [] a;
		a = new byte[6 * _1MB];
	}




	/**
	 * VM参数：-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 */
	@SuppressWarnings("unused")
	public static void testTenuringThreshold() {
		byte [] a1,a2,a3,a4;
		a1 = new byte[_1MB / 4];
		a2 = new byte[3 *_1MB ];
		a3 = new byte[3 *_1MB ];
		a4 = new byte[3 *_1MB ];//Minor GC发生；Survivor空间不够，a2担保进入老年代；分配a3在Eden
		a4 = null;
		a4 = new byte[3 *_1MB ]; //Minor GC发生；a3前一次分配的空间释放；当MaxTenuringThreshold=1 a1进入老年代；分配a3在Eden代


	}


	/**
	 * VM参数：-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
	 */
	@SuppressWarnings("unused")
	public static void testTenuringThreshold2() {
		byte [] a1,a2,a3,a4;
		a1 = new byte[_1MB / 4];
		a2 = new byte[_1MB / 4];

		a3 = new byte[4 *_1MB ];
		a4 = new byte[4 *_1MB ];
		a4 = null;
		a4 = new byte[4 *_1MB ];

	}

	/**
	 * VM参数：-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
	 */
	@SuppressWarnings("unused")
	public static void  testHandlePromotion() {
		byte [] a1,a2,a3,a4,a5,a6,a7;

		a1 = new byte[2 *_1MB];
		a2 = new byte[2 *_1MB];
		a3 = new byte[2 *_1MB];
		a1 = null;

		a4 = new byte[2 *_1MB];
		a5 = new byte[2 *_1MB];
		a6 = new byte[2 *_1MB];

		a4 = null;
		a5 = null;
		a6 = null;
		a7 = new byte[2 *_1MB];

	}





	public static void main(String[] args) {
//		testEdenAlloc();
//		testBigDataAlloc(); //此测试在Paralle Scavenge下无效
		testTenuringThreshold();

	}

}
