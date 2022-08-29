package com.megah.jmsproducer.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megah.jmsproducer.dto.Person;

@RestController
@RequestMapping("produce")
public class Producer {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Queue queue;
	
	@PostMapping("message")
	public ResponseEntity<Object> sendMessage(@RequestBody Person person) {
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			String personAsJson = mapper.writeValueAsString(person);
			
			System.out.println("Person -> " + personAsJson);
			
			jmsTemplate.convertAndSend(queue, personAsJson);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR -> " + e.getMessage());
			e.printStackTrace();
			
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(person, HttpStatus.OK);
	}
}
