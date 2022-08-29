package com.megah.jmsconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.megah.jmsconsumer.*")
public class JmsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsConsumerApplication.class, args);
	}

}
