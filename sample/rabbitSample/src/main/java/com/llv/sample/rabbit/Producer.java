package com.llv.sample.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置 RabbitMQ 地址
		factory.setHost("localhost");
		// 建立到代理服务器到连接
		Connection conn = factory.newConnection();
		// 获得信道
		Channel channel = conn.createChannel();
		channel.queueDeclare("test_lewis", false, false, false, null);
		for (int i = 0; i < 10; i++) {
			channel.basicPublish("", "test_lewis", null, ("queue" + i).getBytes());
		}

		channel.close();
		conn.close();
	}
}
