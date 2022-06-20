package com.megah.springbootlog4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLog4j2Application {
	
	private static final Logger log = LogManager.getLogger(SpringBootLog4j2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLog4j2Application.class, args);
		
		log.info("info");
		log.warn("warn");
		log.error("error");
	}

}
