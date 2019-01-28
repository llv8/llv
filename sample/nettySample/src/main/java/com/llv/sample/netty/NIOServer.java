package com.llv.sample.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class NIOServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
		serverSocketChannel.configureBlocking(false);

		final List<SocketChannel> socketChannelList = Lists.newLinkedList();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					// 处理 每个连接是否可读, 这里的逻辑是 读4个字节后切断连接
					for (SocketChannel socketChannel : Lists.newArrayList(socketChannelList)) {
						try {
							ByteBuffer buf = ByteBuffer.allocate(1024);
							int readed = socketChannel.read(buf);
							if (readed > 0) {
								String content = new String(Arrays.copyOf(buf.array(), readed));
								System.out.println(content);
							}

							if (readed > 0 && buf.array()[0] == 'q') {
								// close
								socketChannel.close();
								// remove from list
								socketChannelList.remove(socketChannel);
							}
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
		}, "server-handler-thread").start();

		// 等待新连接连进来
		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();
			if (socketChannel != null) {
				socketChannel.configureBlocking(false);
				socketChannelList.add(socketChannel);
			}
			Thread.sleep(1000);
		}
	}
}
