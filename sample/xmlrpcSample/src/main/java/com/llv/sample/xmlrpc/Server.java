package com.llv.sample.xmlrpc;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class Server {

	public int sum(int x, int y) {
		return x + y;
	}

	public static void main(String[] args) {

		try {

			System.out.println("Attempting to start XML-RPC Server...");

			WebServer webServer = new WebServer(8080);
			XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
			PropertyHandlerMapping phm = new PropertyHandlerMapping();

			phm.addHandler("sample", Server.class);
			xmlRpcServer.setHandlerMapping(phm);

			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions(false);
			serverConfig.setContentLengthOptional(false);

			webServer.start();

			System.out.println("Started successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
