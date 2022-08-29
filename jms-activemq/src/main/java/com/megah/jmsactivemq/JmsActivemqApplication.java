package com.megah.jmsactivemq;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
//@EnableScheduling
//@EnableJms
//@EnableAsync
public class JmsActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsActivemqApplication.class, args);
	}
	
//	@Bean("threadPoolTaskExecutor")
//	public TaskExecutor getAsyncExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(20);
//		executor.setMaxPoolSize(1000);
//		executor.setWaitForTasksToCompleteOnShutdown(true);
//		executor.setThreadNamePrefix("Async-");
//		return executor;
//	}
	
//	@Bean("taskExecutor")
//	public Executor taskExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(5);
//		executor.setMaxPoolSize(10);
//		executor.setQueueCapacity(500);
//		executor.setThreadNamePrefix("Async2-");
//		executor.initialize();
//		return executor;
//	}
	
//	@Bean
//	public TaskScheduler taskScheduler() {
//	    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//	    threadPoolTaskScheduler.setPoolSize(5);
//	    threadPoolTaskScheduler.setThreadNamePrefix("TaskSched-");
//	    return threadPoolTaskScheduler;
//	}

}
