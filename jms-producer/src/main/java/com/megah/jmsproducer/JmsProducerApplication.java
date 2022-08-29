package com.megah.jmsproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.megah.jmsproducer.*")
public class JmsProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsProducerApplication.class, args);
	}

}
