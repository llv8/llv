package com.llv.sample.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class MultipleIOServer {
	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel servChannel = ServerSocketChannel.open();
		servChannel.configureBlocking(false);
		// 建立一个server socket，到本地端口9999， backlog 1024
		servChannel.socket().setReuseAddress(true);
		servChannel.socket().bind(new InetSocketAddress(9999), 1024);
		// selector 关心 server 上的 ACCEPT 事件
		servChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			try {
				// 阻塞等待 直到有IO事件可读(系统IO事件队列不为空)
				selector.select();
				// 获取 事件 以及 事件所对应的 channel (client server 的连接)
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						if (key.isValid()) {
							// OP_ACCEPT 事件 表示有个新client 完成了三次握手。连接上了本服务器
							if (key.isAcceptable()) {
								// Accept the new connection
								ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
								SocketChannel sc = ssc.accept();
								sc.configureBlocking(false);
								// 将该连接的可读事件 注册到 selector， 到时候他发起请求的时候，我会收到新事件
								sc.register(selector, SelectionKey.OP_READ);
							}
							// OP_READ 事件 说明 client 发的数据已经发到了系统缓冲区，server 可以去读了。
							if (key.isReadable()) {
								SocketChannel sc = (SocketChannel) key.channel();
								// 分配用户台空间, 将数据从内核态 拷贝到 用户态
								ByteBuffer readBuffer = ByteBuffer.allocate(1024);
								int readed = sc.read(readBuffer);
								if (readed > 0) {
									String content = new String(Arrays.copyOf(readBuffer.array(), readed));
									System.out.println(content);
									// 切换读写模式 表示自己目前可以读 [position, limit]
									readBuffer.flip();
									while (readBuffer.hasRemaining()) {
										sc.write(readBuffer);
									}
								} else if (readed < 0) {
									// 对端链路关闭
									key.cancel();
									sc.close();
								} else
									;
							}
						}
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null)
								key.channel().close();
						}
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}

	}
}
