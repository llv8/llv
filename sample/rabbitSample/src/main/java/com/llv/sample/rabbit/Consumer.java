package com.llv.sample.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {
	public static void main(String[] args) throws IOException, TimeoutException {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				public void run() {
					try {
						ConnectionFactory factory = new ConnectionFactory();
						Connection conn = factory.newConnection();
						Channel c = conn.createChannel();
						c.queueDeclare("test_lewis", false, false, false, null);
						while (true) {
							c.basicConsume("test_lewis", true, new DefaultConsumer(c) {

								@Override
								public void handleDelivery(String consumerTag, Envelope envelope,
										BasicProperties properties, byte[] body) throws IOException {
									System.out.println(new String(body)+"--"+Thread.currentThread().getId());
								}

							});
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}).start();
		}

	}
}
