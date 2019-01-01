package com.ddou.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class ProxyExample {

	public static void main1(String[] args) {
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		Class[] clazz = { MyService.class };
		MyService proxy = (MyService) Proxy.newProxyInstance(ProxyExample.class.getClassLoader(), clazz,
				new MyInvocationHandler());
		proxy.show();
	}

	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,
				"/home/lewis/java/java-workspace/demo/com/sun/proxy");
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

class MyInvocationHandler implements InvocationHandler {
	private MyService target = new MyServiceImpl();

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start");
		method.invoke(target, args);
		System.out.println("end");
		return proxy;
	}
}

interface MyService {
	public void show();
}

class MyServiceImpl implements MyService {

	public void show() {
		System.out.println("myserviceimpl");
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
