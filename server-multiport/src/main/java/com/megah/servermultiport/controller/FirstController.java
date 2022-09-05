package com.megah.servermultiport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@RequestMapping("${server.first-port-path}")
	public ResponseEntity<Object> first() {
		return new ResponseEntity<Object>("First", HttpStatus.OK);
	}

}
