package com.megah.jmsactivemq.scheduled_task;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedRateTask {
	
	private static final Logger log = LogManager.getLogger(FixedRateTask.class);
	
	private static final String MESSAGE_QUEUE = "my_message_queue";
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/*
	 * This option should be used when each execution of the task is independent.
	 * Note that scheduled tasks don't run in parallel by default. So even if we
	 * used fixedRate, the next task won't be invoked until the previous one is
	 * done.
	 */
	@Scheduled(fixedRate = 1500) // The time unit is milliseconds 
	public void fixedRateTask() {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		log.info("FIXED RATE TASK");
		
		Map<String, Object> msg = new HashMap<>();
		
		msg.put("id", 1);
		msg.put("message", "Message from FIXED RATE TASK");
		msg.put("timestamp", timestamp.toString());
		
		// Send JMS message
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, msg);
		
	}
	
	/*
	 * To support parallel behavior in scheduled tasks, add the @Async annotation.
	 * This asynchronous task will be invoked, even if the previous task isn't done.
	 */
	@Async("taskExecutor1")
	@Scheduled(fixedRate = 500)
	public void fixedRateTaskAsync() {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		log.info("FIXED RATE TASK ASYNC");
		
		Map<String, Object> msg = new HashMap<>();
		
		msg.put("id", 1);
		msg.put("message", "Message from FIXED RATE TASK ASYNC");
		msg.put("timestamp", timestamp.toString());
		
		// Send JMS message
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, msg);
	}

}
