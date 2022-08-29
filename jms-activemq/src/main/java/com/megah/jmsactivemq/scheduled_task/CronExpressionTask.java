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
public class CronExpressionTask {
	
	private static final Logger log = LogManager.getLogger(CronExpressionTask.class);
	
	private static final String MESSAGE_QUEUE = "my_message_queue";
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Scheduled(cron = "0/3 * * * * ?") // Every 3 seconds
//	@Scheduled(cron = "${sched.cron}")
	public void cronExpressionTask() {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		log.info("CRON TASK");
		
		Map<String, Object> msg = new HashMap<>();
		
		msg.put("id", 1);
		msg.put("message", "Message from CRON TASK");
		msg.put("timestamp", timestamp.toString());
		
		// Send JMS message
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, msg);
	}
}
