package com.llv.sample.jaxws.manual.ws;
 
import javax.jws.WebService;
 
//Service Implementation
@WebService(endpointInterface = "com.llv.sample.jaxws.manual.ws.HelloWorld",serviceName="HelloWorldImplService")
public class HelloWorldImpl implements HelloWorld{
 
	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
 
}