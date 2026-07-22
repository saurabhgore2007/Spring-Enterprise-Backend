package com.saurabh.spring.day05.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "com.saurabh.spring.day05.beanlifecycle")
public class AppConfig {

	 @Bean(initMethod = "init", destroyMethod = "destroy")
	 public DatabaseConnection databaseConnection() {
	      return new DatabaseConnection();
	 }
}
