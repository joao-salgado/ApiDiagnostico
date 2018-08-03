package com.diagnostico.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

	@Bean
	public ThreadPoolTaskExecutor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(30);
		executor.setQueueCapacity(15);
		executor.setThreadNamePrefix("threadPoolTaskExecutor-");
		executor.initialize();
		return executor;
	}
	
}
