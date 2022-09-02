package com.megah.servletfilter3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.megah.servletfilter3")
public class ServletFilter3Application {

	public static void main(String[] args) {
		SpringApplication.run(ServletFilter3Application.class, args);
	}

}
