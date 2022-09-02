package com.megah.servletfilter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.megah.servletfilter2")
public class ServletFilter2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServletFilter2Application.class, args);
	}

}
