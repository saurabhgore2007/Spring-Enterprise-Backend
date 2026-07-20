package com.saurabh.spring.day04.beanscope.challenge;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Car c1 = context.getBean(Car.class);
		Car c2 = context.getBean(Car.class);

		System.out.println();

		c1.show();

		System.out.println();

		c2.show();

		System.out.println();

		System.out.println(c1 == c2);
	}

}
