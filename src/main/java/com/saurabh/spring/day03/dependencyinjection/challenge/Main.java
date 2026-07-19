package com.saurabh.spring.day03.dependencyinjection.challenge;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		UserService service = context.getBean(UserService.class);

		service.registerUser();
	}

}
