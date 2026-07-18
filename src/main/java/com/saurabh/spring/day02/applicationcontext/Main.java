package com.saurabh.spring.day02.applicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		System.out.println("Application Starting...\n");

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\nSpring Container Ready\n");

        Car car = context.getBean(Car.class);

        car.drive();
        
        Car car1 = context.getBean(Car.class);
        Car car2 = context.getBean(Car.class);

        System.out.println(car1 == car2);
	}

}	
