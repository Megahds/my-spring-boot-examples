package com.megah.servletfilter2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greetings")
public class HelloController {
	
	private static final Logger log = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("hello")
	public ResponseEntity<Object> hello() {
		
		log.info("HELLO :)");
		
		return new ResponseEntity<Object>("Hello :)", HttpStatus.OK);
	}
	
	@RequestMapping("halo")
	public ResponseEntity<Object> halo() {
		
		log.info("HALO :)");
		
		return new ResponseEntity<Object>("Halo :)", HttpStatus.OK);
	}
}