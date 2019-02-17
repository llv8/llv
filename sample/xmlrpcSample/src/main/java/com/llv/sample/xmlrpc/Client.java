package com.llv.sample.xmlrpc;

import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class Client {
	public static void main(String[] args) {

		try {
			XmlRpcClientConfigImpl xmlConfig = new XmlRpcClientConfigImpl();
			xmlConfig.setServerURL(new URL("http://localhost:8080/xmlrpc"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(xmlConfig);

			Vector params = new Vector();

			params.addElement(new Integer(17));
			params.addElement(new Integer(13));

			Object result = client.execute("sample.sum", params);

			int sum = ((Integer) result).intValue();
			System.out.println("The sum is: " + sum);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
