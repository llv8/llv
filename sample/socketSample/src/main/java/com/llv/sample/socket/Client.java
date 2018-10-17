package com.llv.sample.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 8883);
		OutputStream os = socket.getOutputStream();
		os.write("hello ,  this is from client".getBytes());
		os.flush();
		//os.close();
		
		InputStream is = socket.getInputStream();
		byte[] result = new byte[100];
		is.read(result);
		System.out.println(new String(result));
		
		
		
		os = socket.getOutputStream();
		os.write("hello ,  this is from client222222".getBytes());
		os.flush();
		
		
		socket.close();
	}
}
