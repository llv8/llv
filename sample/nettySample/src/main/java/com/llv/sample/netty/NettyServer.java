package com.llv.sample.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
	public class EchoHandler extends SimpleChannelInboundHandler {
		@Override
		public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
			// 为什么 这里可以强转String？
			String in = (String) msg;
			System.out.print(in);
			// 将数据写回
			ctx.writeAndFlush(in);
		}
	}

	public void run() throws Exception {
		EventLoopGroup acceptGroup = new NioEventLoopGroup(1); // 指定 Acceptor 线程池大小
		EventLoopGroup ioGroup = new NioEventLoopGroup(1); // 指定 NIO线程池大小
		try {
			ServerBootstrap b = new ServerBootstrap(); // 创建 ServerBootstrap 对象，他是Netty 用于启动NIO
														// 服务端的辅助启动类，目的是降低服务端的开发复杂度。
			b.group(acceptGroup, ioGroup).channel(NioServerSocketChannel.class) // 指定使用 java 的NioServerSocketChannel
					.childHandler(new ChannelInitializer<SocketChannel>() { // 创建 IOThread 的 pipeline
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new StringDecoder()).addLast(new StringEncoder())// 添加echo Handler
									.addLast(new EchoHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128) // server socket config backlog 设置为 128
					.childOption(ChannelOption.SO_KEEPALIVE, true); // client socket config 设置 keepalive = true
			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(9999); // 同步等待绑定本地端口
			f.addListener(new GenericFutureListener<Future<? super Void>>() {
				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					Thread.sleep(1000000);
					System.out.println("connect successfully!");
				}
			});
			// 等待服务器 socket 关闭 。
			// 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
			ChannelFuture closeFutrue = f.channel().closeFuture().sync();
			/*
			 * closeFutrue.addListener(new GenericFutureListener<Future<? super Void>>() {
			 * 
			 * @Override public void operationComplete(Future<? super Void> future) throws
			 * Exception { System.out.println("close successfully!"); } });
			 */
		} finally {
			// 释放两个线程池
			acceptGroup.shutdownGracefully();
			ioGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new NettyServer().run();
	}
}
