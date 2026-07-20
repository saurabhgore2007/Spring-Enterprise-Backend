package com.saurabh.spring.day04.beanscope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")   try with uncomment at this line
public class UserService {

	public UserService() {
        System.out.println("UserService Constructor Called");
    }
	
	public void display() {
        System.out.println("Object Address : " + this);
    }
}
