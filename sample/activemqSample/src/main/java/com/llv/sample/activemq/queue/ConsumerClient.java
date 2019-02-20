package com.llv.sample.activemq.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerClient {
	public static void main(String[] args) throws JMSException {
		MessageConsumer consumer = createConsumer();
		push(consumer);
		/*
		 * for (int i = 0; i < 20; i++) { // pull mode // pull(consumer); // push mode
		 * push(consumer); }
		 */
	}

	private static MessageConsumer createConsumer() throws JMSException {
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		// Create a Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("TEST.FOO");

		// Create a MessageConsumer from the Session to the Topic or Queue
		MessageConsumer consumer = session.createConsumer(destination);

		return consumer;
	}

	private static void handleMessage(Message message) throws JMSException {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.out.println("Received: " + text);
		} else {
			System.out.println("Received: " + message);
		}
	}

	private static void pull(MessageConsumer consumer) throws JMSException {
		handleMessage(consumer.receive(1000));
	}

	private static void push(MessageConsumer consumer) throws JMSException {
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message arg0) {
				// TODO Auto-generated method stub
				try {
					handleMessage(arg0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
