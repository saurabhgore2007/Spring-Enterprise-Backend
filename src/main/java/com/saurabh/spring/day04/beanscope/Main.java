package com.saurabh.spring.day04.beanscope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		UserService user1 = context.getBean(UserService.class);
        UserService user2 = context.getBean(UserService.class);
        UserService user3 = context.getBean(UserService.class);

        System.out.println();
        
        user1.display();
        user2.display();
        user3.display();

        System.out.println();

        System.out.println(user1 == user2);
        System.out.println(user2 == user3);
        System.out.println(user1 == user3);
	}

}
