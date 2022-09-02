package com.megah.servletfilter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class ServletConfiguration {

//	@Bean
//	OncePerRequestFilter mdcFilter() {
//		return new OncePerRequestFilter() {
//			@Override
//			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//					FilterChain filterChain) throws ServletException, IOException {
//				System.out.println("Ip address -> " + request.getRemoteAddr());
//				MDC.put("x-debug-token", UUID.randomUUID().toString());
//				filterChain.doFilter(request, response);
//				MDC.remove("x-debug-token");
//			}
//		};
//	}

	@Bean
	OncePerRequestFilter myFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {

				System.out.println("Remote Host -> " + request.getRemoteHost());
				System.out.println("Remote Address -> " + request.getRemoteAddr());
				filterChain.doFilter(request, response);
			}
		};
	}
}
