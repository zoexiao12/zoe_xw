package com.zoe.study.java.java7.chapter9;

public class ServiceConsumer {
	
	@SuppressWarnings("unused")
	public void consume() throws Exception {
		String serviceName = "com.demo.java.java7.chapter9.VersionizedService";
		Versionized v1 = ServiceFactory.getService(serviceName, "v1");
		Versionized v2 = ServiceFactory.getService(serviceName, "v2");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
