package com.java.concepts;

import java.lang.reflect.Method;

public class ReflectionDemo {
	public static void main(String[] args) throws Exception {

		Class c = Class.forName("com.java.concepts.A");
		Object o = c.getDeclaredConstructor().newInstance();
		
		Method m = c.getDeclaredMethod("message", null);
		m.setAccessible(true);
		m.invoke(o, null);
	}
}