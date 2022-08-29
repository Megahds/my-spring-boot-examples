//package com.megah.jmsconsumer.consumer;
//
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//	
//	@JmsListener(destination = "${activemq.queue}")
//	public void consumeMessage(String message) {
//		System.out.println("Message received from activemq queue>>>" + message);
//	}
//
//}
