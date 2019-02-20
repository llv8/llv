package com.llv.sample.activemq.queue;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerClient {
	public static void main(String[] args) throws Exception {
		Session session = createSession();
		MessageProducer producer = createProducer(session);
		for (int i = 0; i < 20; i++) {
			send(producer, session, "" + i);
			Thread.sleep(10000L);
		}

	}

	private static MessageProducer createProducer(Session session) throws JMSException {

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("TEST.FOO");

		// Create a MessageProducer from the Session to the Topic or Queue
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		return producer;
	}

	private static Session createSession() throws JMSException {
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		// Create a Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Create a Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		return session;
	}

	private static void send(MessageProducer producer, Session session, String str) throws JMSException {
		// Create a messages
		String text = "send:" + str;
		TextMessage message = session.createTextMessage(text);

		// Tell the producer to send the message
		System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
		producer.send(message);
	}

}
