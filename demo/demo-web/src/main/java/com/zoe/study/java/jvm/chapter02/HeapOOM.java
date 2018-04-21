package com.zoe.study.java.jvm.chapter02;

import java.util.ArrayList;
import java.util.List;


/**
 * VM Args:
 * -verbose:gc -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=F:\head_dump\heapdump.hprof
 * @author zoe
 */
public class HeapOOM {
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		
		while(true) {
			list.add(new OOMObject());
		}
	}
}
