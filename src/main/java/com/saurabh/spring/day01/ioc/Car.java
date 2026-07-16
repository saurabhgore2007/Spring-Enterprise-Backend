package com.saurabh.spring.day01.ioc;

public class Car {

	private Engine engine;
	
	public Car(Engine engine) {
		this.engine = engine;
	}
	public void drive() {
		engine.start();
        System.out.println("Car Running");
	}
}
