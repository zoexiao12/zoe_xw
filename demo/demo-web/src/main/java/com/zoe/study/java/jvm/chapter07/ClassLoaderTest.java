package com.zoe.study.java.jvm.chapter07;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zoe on 2017/11/7.
 */
public class ClassLoaderTest {



    public static void main(String [] args) throws Exception{
        ClassLoader cl = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null) {
                        return super.loadClass(name);
                    }
                    byte [] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = cl.loadClass("com.zoe.study.java.jvm.chapter07.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }
}
