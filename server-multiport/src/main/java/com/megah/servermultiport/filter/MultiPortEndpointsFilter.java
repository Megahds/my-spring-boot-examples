package com.megah.servermultiport.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiPortEndpointsFilter implements Filter {

	private int secondPortNum = 0;
	private String secondPathPrefix;
	private final Logger log = LoggerFactory.getLogger(getClass().getName());

	public MultiPortEndpointsFilter(String secondPort, String secondPathPrefix) {

		if (secondPort != null && secondPathPrefix != null && !"null".equals(secondPathPrefix)) {
			secondPortNum = Integer.valueOf(secondPort);
			this.secondPathPrefix = secondPathPrefix;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
		log.debug("trusted port filter init: " + secondPortNum + ":" + secondPathPrefix);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (secondPortNum != 0) {

			if (isRequestForSecondEndpoint(servletRequest) && servletRequest.getLocalPort() != secondPortNum) {
				log.warn("denying request for second endpoint on other port");
				((ResponseFacade) servletResponse).setStatus(404);
				servletResponse.getOutputStream().close();
				return;
			}

			if (!isRequestForSecondEndpoint(servletRequest) && servletRequest.getLocalPort() == secondPortNum) {
				log.warn("denying request for other endpoint on second port");
				((ResponseFacade) servletResponse).setStatus(404);
				servletResponse.getOutputStream().close();
				return;
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private boolean isRequestForSecondEndpoint(ServletRequest servletRequest) {
		return ((RequestFacade) servletRequest).getRequestURI().startsWith(secondPathPrefix);
	}

	@Override
	public void destroy() {
	}
}
