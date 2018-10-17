package com.llv.sample.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket ss = new ServerSocket(8883);
		while (true) {
			Socket s = ss.accept();
			while(true) {
				InputStream is = s.getInputStream();
				byte[] result = new byte[100];
				is.read(result);
				System.out.println(new String(result));

				OutputStream os = s.getOutputStream();
				os.write("server response".getBytes());
				os.flush();
			}

			// os.close();
			// s.close();
			// ss.close();

		}
	}
}
