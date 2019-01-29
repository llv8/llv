package com.llv.sample.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class MultipleIOClient {
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
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			int readed = socket.read(readBuffer);
			if (readed > 0) {
				String content = new String(Arrays.copyOf(readBuffer.array(), readed));
				System.out.println(content);
			}
		}
	}
}
