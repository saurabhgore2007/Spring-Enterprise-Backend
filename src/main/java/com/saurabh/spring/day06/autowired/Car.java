package com.saurabh.spring.day06.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

	private Engine engine;
	
	public Car() {
        System.out.println("Default Constructor");
    }

	@Autowired
    public Car(Engine engine) {
        this.engine = engine;
        System.out.println("Parameterized Constructor");
    }
}
