package com.megah.jmsactivemq.util;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PrintMessage {
	
	private static final Logger log = LogManager.getLogger(PrintMessage.class);
	
	@Async("taskExecutor2")
	public void printMessage(List<Map<String, Object>> messages) {
		
		log.info("PRINT MESSAGE TASK");
		
		messages.forEach(msg -> {
			System.out.println(msg.get("timestamp") + " - {Id: " + msg.get("id") + ", Message: " + msg.get("message") + "}");
		});
		
	}

}
