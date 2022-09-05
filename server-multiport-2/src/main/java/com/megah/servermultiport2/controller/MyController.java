package com.megah.servermultiport2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@RequestMapping("hello")
	public ResponseEntity<Object> hello() {
		return new ResponseEntity<Object>("HELLO :)", HttpStatus.OK);
	}
}
