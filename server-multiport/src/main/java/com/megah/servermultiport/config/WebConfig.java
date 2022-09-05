package com.megah.servermultiport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.megah.servermultiport.filter.MultiPortEndpointsFilter;

@Configuration
public class WebConfig {

	@Value("${server.second-port:null}")
	private String secondPort;

	@Value("${server.second-port-path:null}")
	private String secondPathPrefix;

	@Bean
	public FilterRegistrationBean<MultiPortEndpointsFilter> multiEndpointsFilter() {
		return new FilterRegistrationBean<>(new MultiPortEndpointsFilter(secondPort, secondPathPrefix));
	}
}
