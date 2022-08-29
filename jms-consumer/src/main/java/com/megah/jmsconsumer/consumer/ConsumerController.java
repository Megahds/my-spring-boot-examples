package com.megah.jmsconsumer.consumer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megah.jmsconsumer.dto.Person;

@RestController
@RequestMapping("consume")
public class ConsumerController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Queue queue;
	
	@GetMapping("message")
	public ResponseEntity<Object> consumeMessage() {
		
		Person person = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonMessage = (String) jmsTemplate.receiveAndConvert(queue);
			
			System.out.println("Person -> " + jsonMessage);
			
			person = mapper.readValue(jsonMessage, Person.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR -> " + e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<Object>(person, HttpStatus.OK);
	}
}
