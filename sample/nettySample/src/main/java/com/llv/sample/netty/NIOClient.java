package com.llv.sample.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
	public static void main(String[] args) throws IOException, InterruptedException {
		SocketChannel socket = SocketChannel.open();
		socket.connect(new InetSocketAddress("127.0.0.1", 9999));
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			byte[] bytes = input.readLine().getBytes();
			ByteBuffer writeBuf = ByteBuffer.wrap(bytes);
			while (writeBuf.hasRemaining()) {
				socket.write(writeBuf);
			}
		}
	}
}
