package com.megah.jmsactivemq.receiver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.megah.jmsactivemq.util.PrintMessage;

@Service
public class MessageReceiver {
	
	private static final Logger log = LogManager.getLogger(MessageReceiver.class);
	
	private static final String MESSAGE_QUEUE = "my_message_queue";
	
	private List<Map<String, Object>> messages = new ArrayList<>();
	
	@Autowired
	private PrintMessage printMsg;
	
	@JmsListener(destination = MESSAGE_QUEUE, containerFactory = "myFactory")
	public void receiveMessage(Map<String, Object> msg)
	{
		
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
//		System.out.println(timestamp + " - " + "Received message -> " + msg);
//		System.out.println(timestamp + " - " + "Id: " + msg.get("id"));
//		System.out.println(timestamp + " - " + "Message: " + msg.get("message"));
		
		messages.add(msg);
		
		if (messages.size() == 10) {
			
			printMsg.printMessage(messages);
			
			messages = new ArrayList<>();
		}
	}
	
}
