package com.saurabh.spring.day05.beanlifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//		DatabaseConnection db = context.getBean(DatabaseConnection.class);
        System.out.println("\nApplication Running...\n");

        context.close();
	}

}
