package com.zoe.study.java.java7.chapter2.script;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class JavaScriptUtil {
	public static ScriptEngine getScriptEngine(String engineName) throws ScriptException{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName(engineName);
		if(engine == null) {
			throw new RuntimeException("找不到 JavaScript 语言的执行引擎！");
		}
		return engine;
	}
	
	public static ScriptEngine getScriptEngine() throws ScriptException{
		return getScriptEngine("JavaScript");
	}
	
	
	
	public static CompiledScript compile(String scriptText) throws ScriptException {
		CompiledScript cs = null;
		ScriptEngine engine = getScriptEngine();
		if(engine instanceof Compilable){
			Compilable compilable = (Compilable)engine;
			cs = compilable.compile(scriptText);
		}
		return cs;
	}
	
	
	public static void main(String [] args)  throws Exception {
		
		ScriptEngine engine = getScriptEngine("nashorn");
	
		engine.eval(" if (typeof println == 'undefined') this.println = print; println('hello world!');");
	}
}
