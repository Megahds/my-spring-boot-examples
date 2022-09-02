package com.megah.servletfilter2.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		log.info("Remote Host:" + request.getRemoteHost());
		log.info("Remote Address:" + request.getRemoteAddr());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
