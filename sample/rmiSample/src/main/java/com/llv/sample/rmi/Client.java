package com.llv.sample.rmi;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String key = "Greeting";
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
		Greeting value = (Greeting) registry.lookup(key);
		System.out.println(key + " reported: " + value.greet("lewis"));
	}
}
