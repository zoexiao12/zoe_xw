package com.zoe.study.java.java7.chapter2.dynamic;

import java.lang.invoke.MethodType;

@SuppressWarnings("unused")
public class MethodTypeTest {
	
	public void generateMethodTypes(){
		//String -- String length()
		MethodType mt1 = MethodType.methodType(int.class);
		
		//String --String concat(String str)
		MethodType mt2 = MethodType.methodType(String.class, String.class);
		
//		String -- void getChars(int srcBegin,int  srcEnd, char [] dst, int dstBegin);
		MethodType mt3 = MethodType.methodType(void.class,int.class,int.class,char [].class,int.class);
		
//		String -- boolean startsWith(String prefix)
		MethodType mt4 = MethodType.methodType(boolean.class,mt2);
	}
	
	public void generateGenericMethodTypes(){
//		Object (Oject,Object,Object)
		MethodType mt1 = MethodType.genericMethodType(3);
//		Object (Object,Object,Object [])
		MethodType mt2 = MethodType.genericMethodType(2, true);
	}
	
	public void generateMethodTypesFromDescript(){
//		String (String)
		ClassLoader cl = this.getClass().getClassLoader();
		String descriptor = "(Ljava/lang/String;)Ljava/lang/String;";
		MethodType mt2 = MethodType.fromMethodDescriptorString(descriptor, cl);
	}
	
	public void changeMethodType(){
		// String (int,int)
		MethodType mt = MethodType.methodType(String.class,int.class,int.class);
		
		//String (int,int,float)
		mt = mt.appendParameterTypes(float.class);
		
		//String (int,double,long,int,float)
		mt = mt.insertParameterTypes(1, double.class,long.class);
		
		//String (int,double,int,float)
		mt = mt.dropParameterTypes(2, 3);
		
		//String (int,double,String,float)
		mt = mt.changeParameterType(2, String.class);
		
		//String (int,double,String,float)
		mt = mt.changeReturnType(void.class);
	}
	
	public void wrapAndGeneric() {
		//Integer(int, double)
		MethodType mt = MethodType.methodType(Integer.class, int.class,double.class);
		
		//Integer(Integer, Double)
		MethodType wrap = mt.wrap();
		
		//int(int, double)
		MethodType unwrap = mt.unwrap();
		
		//Object(Object, Object)
		MethodType generic = mt.generic();
		
		//Object(int, double)
		MethodType erase = mt.erase();
		
		
		
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
