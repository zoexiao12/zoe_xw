package com.zoe.study.java.java7.chapter2.invokedynamic;

public class MethodInvokeTypes {
	
	public void invoke() {
		SampleInterface sample = new Sample();   //invokespecial
		sample.sampleMethodInterface();          //invokeinterface
		
		Sample newSample = new Sample();	    //invokespecial
		newSample.normalMethod();               //invokevirtual
		Sample.staticSampleMethod();            //invokedynamic
		
	}

}
