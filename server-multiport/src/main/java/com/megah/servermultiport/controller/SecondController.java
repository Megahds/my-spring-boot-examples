package com.megah.servermultiport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondController {
	
	@RequestMapping("${server.second-port-path}")
	public ResponseEntity<Object> second() {
		return new ResponseEntity<Object>("Second", HttpStatus.OK);
	}
}
