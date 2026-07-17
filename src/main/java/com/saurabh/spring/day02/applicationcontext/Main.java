package com.saurabh.spring.day02.applicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context =
		        new AnnotationConfigApplicationContext(AppConfig.class);
		
		Engine engine = context.getBean(Engine.class);
		
		engine.start();
	}

}
