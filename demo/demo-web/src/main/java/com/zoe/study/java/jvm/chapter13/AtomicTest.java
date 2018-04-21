package com.zoe.study.java.jvm.chapter13;

import akka.dispatch.ExecutorServiceFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	private static final int THREAD_COUNT = 20;
	public static AtomicInteger race = new AtomicInteger(0);
	public static void increase() {
		race.incrementAndGet();
	}


	public static void main(String[] args) {
		ExecutorService pools = Executors.newFixedThreadPool(THREAD_COUNT);
		for (int i = 0; i < THREAD_COUNT; i++) {
			pools.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j <10000 ; j++) {
						increase();
					}
				}
			});
		}
		pools.shutdown();
		while (true) {
			if(pools.isTerminated()){
				break;
			}
		}
		System.out.println(race);
	}
}
