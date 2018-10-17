package com.llv.sample;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibAgentSimple {

	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/home/lewis/cglibdump");
		cgLibProxy();
	}

	private static void cgLibProxy() {
		MyService helloService = new MyServiceImpl();
		MyMethodIntercepter intercepter = new MyMethodIntercepter(helloService);
		MyService proxy = (MyService) Enhancer.create(MyService.class, intercepter);
		System.err.println("Proxy class name is " + proxy.getClass().getName());
		proxy.show();
	}

}

class MyMethodIntercepter implements MethodInterceptor {
	private Object myService;

	public MyMethodIntercepter(Object target) {
		myService = target;
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("cglib start");
		Object result = proxy.invoke(myService, args);
		System.out.println("cglib end");
		return result;
	}

}
