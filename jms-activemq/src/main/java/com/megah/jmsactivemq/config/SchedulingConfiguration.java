package com.megah.jmsactivemq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(basePackages = "com.megah.jmsactivemq")
@EnableScheduling
public class SchedulingConfiguration {
	
	@Bean
	public TaskScheduler taskScheduler() {
	    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
	    threadPoolTaskScheduler.setPoolSize(6);
	    threadPoolTaskScheduler.setThreadNamePrefix("TaskSched-");
	    return threadPoolTaskScheduler;
	}

}
