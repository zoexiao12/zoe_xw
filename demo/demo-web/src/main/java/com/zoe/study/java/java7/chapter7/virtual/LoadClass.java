package com.zoe.study.java.java7.chapter7.virtual;

import org.springframework.asm.ClassWriter;
import org.springframework.asm.Opcodes;

public class LoadClass extends ClassLoader {
	
	private void createAndLoadClass(String className) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, className, null, "java/lang/Object", null);
		cw.visitEnd();
		byte [] classData = cw.toByteArray();
		this.defineClass(className, classData, 0, classData.length);
	}
	
	public void loadManyClass() {
		int num = 5000;
		String calssNamePrefix = "ManyClass";
		String className = null;
		for(int i=0;i<num;i++) {
			className = calssNamePrefix + i;
			createAndLoadClass(className);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
