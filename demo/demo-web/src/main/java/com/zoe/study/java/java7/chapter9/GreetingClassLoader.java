package com.zoe.study.java.java7.chapter9;

import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

public class GreetingClassLoader extends ClassLoader implements Opcodes {
	
	private String message;
	 
	public GreetingClassLoader(String message) {
		this.message = message;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	private byte [] generateClassData(String className){
		className = className.replace("\\.", "/");
		
		ClassWriter writer = new ClassWriter(0);
		writer.visit(V1_7,ACC_PUBLIC+ACC_SUPER,className,null,"java/lang/Object",null);
		MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		
		mv.visitCode();
		mv.visitVarInsn(AALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
		mv.visitLdcInsn(message);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/PrintStream", "println", "(Ljava/lang/String;)V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(2, 1);
		mv.visitEnd();
		return writer.toByteArray();
	}
	

}
