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
public class FixedDelayTask {
	
	private static final Logger log = LogManager.getLogger(FixedDelayTask.class);
	
	private static final String MESSAGE_QUEUE = "my_message_queue";
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/*
	 * In this case, the duration between the end of the last execution and the
	 * start of the next execution is fixed. The task always waits until the
	 * previous one is finished.
	 * This option should be used when it’s mandatory that the previous execution is
	 * completed before running again.
	 */
	@Scheduled(fixedDelay = 2000)
	public void fixedDelayTask() {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		log.info("FIXED DELAY TASK");
		
		Map<String, Object> msg = new HashMap<>();
		
		msg.put("id", 1);
		msg.put("message", "Message from FIXED DELAY TASK");
		msg.put("timestamp", timestamp.toString());
		
		// Send JMS message
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, msg);
	}

}
