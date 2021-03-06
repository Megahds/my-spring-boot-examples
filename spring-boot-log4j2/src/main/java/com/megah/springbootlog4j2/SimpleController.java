package com.megah.springbootlog4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
	
	private static final Logger log = LogManager.getLogger(SimpleController.class);
	
	@RequestMapping(value = "hello")
	public ResponseEntity<Object> hello() {
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");
		return ResponseEntity.ok("HELLO");
	}

}
