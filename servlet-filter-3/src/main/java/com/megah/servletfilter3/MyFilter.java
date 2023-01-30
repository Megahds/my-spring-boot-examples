package com.megah.servletfilter3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

//@WebFilter(urlPatterns = "/greetings/*")
@WebFilter(urlPatterns = "/greetings/hello")
public class MyFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		log.info("Initializing filter :{}", this);
	}
	
//	@Value("${ip.white-list}")
//	private String ipWhiteList;
	
	@Autowired
	private Environment env;
	
	private final ObjectMapper mapper;
	
	MyFilter(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		log.info("Executing MyFilter");
		
		// TODO: get ip whitelist
		String[] activeProfiles = env.getActiveProfiles();

		Properties prop = new Properties();
		String fileName = "application.properties";

		String fileLoc = env.getProperty("spring.config.location");

		if (fileLoc != null) {
			fileLoc = fileLoc.replaceFirst("optional:", "");
		}

		if (activeProfiles.length == 0) {
			prop.load(MyFilter.class.getClassLoader().getResourceAsStream(fileName));
		} else {
			fileName = "application-" + activeProfiles[0] + ".properties";
			File file = new File(fileLoc + fileName);
			InputStream in = new FileInputStream(file);
			prop.load(in);
		}
		
		String ipWhiteList = prop.getProperty("ip.white-list");
		
		// TODO: validasi ip
		int check = 0;

		String[] listIp = ipWhiteList.split("\\|");

		String ipAddress = request.getRemoteAddr();
		System.out.println("IP : " + ipAddress);

		for (String x : listIp) {
			if (x.equals(ipAddress)) {
				check = 1;
				break;
			}
		}

		if (check == 0) {
			
//			((ResponseFacade) response).setStatus(403);
//			((ResponseFacade) response).sendError(403, "Your IP don't have access!");
//			response.getOutputStream().close();
			
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			
			Map<String, Object> errorMessage = new HashMap<>();
			errorMessage.put("response code", "01");
			errorMessage.put("response desc", "Your IP don't have access!");
			
			httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
			httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			
			mapper.writeValue(httpServletResponse.getWriter(), errorMessage);
			
			return;
		}
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		log.warn("Destructing filter :{}", this);
	}

}
