package com.megah.jmsactivemq.scheduled_task;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedDelayInitialDelayTask {
	
	private static final Logger log = LogManager.getLogger(FixedDelayInitialDelayTask.class);
	
	private static final String MESSAGE_QUEUE = "my_message_queue";
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/*
	 * The task will be executed the first time after the initialDelay value, 
	 * and it will continue to be executed according to the fixedDelay.
	 */
	@Scheduled(fixedDelay = 2000, initialDelay = 500)
	public void fixedDelayInitialDelayTask() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		log.info("FIXED DELAY + INITIAL DELAY TASK");
		
		Map<String, Object> msg = new HashMap<>();
		
		msg.put("id", 1);
		msg.put("message", "Message from FIXED DELAY + INITIAL DELAY TASK");
		msg.put("timestamp", timestamp.toString());
		
		// Send JMS message
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, msg);
	}
}
