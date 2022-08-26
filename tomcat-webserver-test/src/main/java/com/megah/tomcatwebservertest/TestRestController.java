package com.megah.tomcatwebservertest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("server")
public class TestRestController {
	
	@PostMapping(value = "test", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> test(HttpServletRequest request, @RequestBody Map<String, Object> requestBody) {
		
		Map<String, Object> response = new HashMap<>();
		
//		System.out.println("ContentType: " + request.getContentType());
		System.out.println("RemoteAddr: " + request.getRemoteAddr());
		System.out.println("LocalAddr: " + request.getLocalAddr());
		
		Enumeration<String> headerNames = request.getHeaderNames();
		
		System.out.println("HEADERS: ");
		while (headerNames.hasMoreElements()) {
		    String headerName = headerNames.nextElement();
		    System.out.println(headerName + "-> " + request.getHeader(headerName));
		}
		
		System.out.println("Request Body: " + requestBody);
		
		response = requestBody;
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
}
