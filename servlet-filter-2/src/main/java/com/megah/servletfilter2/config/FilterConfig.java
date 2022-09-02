package com.megah.servletfilter2.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.megah.servletfilter2.filters.MyFilter;
import com.megah.servletfilter2.filters.RequestResponseLoggingFilter;
import com.megah.servletfilter2.filters.TransactionFilter;

@Configuration
public class FilterConfig {
	
//	@Bean
	public FilterRegistrationBean<MyFilter> myFilter() {
		FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new MyFilter());
		registrationBean.setOrder(1);

		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<TransactionFilter> transactionFilter() {
		FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new TransactionFilter());
		registrationBean.setOrder(2);

		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
		FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new RequestResponseLoggingFilter());
//		registrationBean.addUrlPatterns("/greetings/*");
//		registrationBean.addUrlPatterns("/greetings/hello");
//		registrationBean.addUrlPatterns("/greetings/halo");
		Collection<String> urlPatterns = new HashSet<String>();
//		urlPatterns.add("/greetings/*");
		urlPatterns.add("/greetings/hello");
//		urlPatterns.add("/greetings/halo");
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(3);

		return registrationBean;
	}
}
