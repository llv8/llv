package com.llv.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKAgentSimple {
	public static void main(String[] args) {
		// dump路径为 "/{currentProject}/{package}/$Proxy0.class"
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		Class[] clazz = { MyService.class };
		MyService proxy = (MyService) Proxy.newProxyInstance(JDKAgentSimple.class.getClassLoader(), clazz,
				new MyInvocationHandler());
		proxy.show();
	}
}

class MyInvocationHandler implements InvocationHandler {
	private MyService target = new MyServiceImpl();

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start");
		method.invoke(target, args);
		System.out.println("end");
		return proxy;
	}
}
