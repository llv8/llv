package com.llv.sample.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GreetingObject extends UnicastRemoteObject implements Greeting {
	protected GreetingObject() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;
	private String fmtString = "Hello, %s";

	public String greet(String name) {
		return String.format(this.fmtString, name);
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		String key = "Greeting";
		Greeting value = new GreetingObject();
		//if GreetingObject extends UnicastRemoteObject,then you do not need to explicitly export it using UnicastRemoteObject.exportObject() methods
		//Greeting stub = (Greeting) UnicastRemoteObject.exportObject(greeting, 0);
		Registry registry = LocateRegistry.createRegistry(1099);
		//if start "rmiregistry" stand alone through command line, you can get registry by LocateRegistry.getRegistry(1099)
		registry.bind(key,value);;
		System.out.println("Greeting bound to \"" + key + "\"");
	}
}
