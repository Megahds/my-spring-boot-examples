package com.megah.springsecurityjwt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("hello")
	public ResponseEntity<Object> hello() {
		return new ResponseEntity<Object>("Hello", HttpStatus.OK);
	}
}
