package com.llv.sample.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BIOClient {
	public static void main(String[] args) throws Exception {
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
		Socket socket = new Socket();
		socket.connect(address);

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String consoleStr = input.readLine();
				out.println(consoleStr);
				String response = is.readLine();
				System.out.println(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}

}
