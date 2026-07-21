package com.saurabh.spring.day05.beanlifecycle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Scope("prototype")
public class DatabaseConnection {

	 public DatabaseConnection() {
	        System.out.println("1. Constructor : DatabaseConnection Bean Created");
	  }
	 
	 @PostConstruct
	 public void init() {
		 	System.out.println("2. @PostConstruct : Opening Database Connection");
	  }

	 @PreDestroy
	 public void destroy() {
	       	System.out.println("3. @PreDestroy : Closing Database Connection");
	 }
}
