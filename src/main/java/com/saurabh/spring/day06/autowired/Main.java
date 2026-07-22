package com.saurabh.spring.day06.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        context.close();
	}

}
