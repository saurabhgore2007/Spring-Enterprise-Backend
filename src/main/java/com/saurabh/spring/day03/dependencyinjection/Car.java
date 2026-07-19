package com.saurabh.spring.day03.dependencyinjection;

import org.springframework.stereotype.Component;

@Component
public class Car {

	private final Engine engine;

	public Car(Engine engine) {
        this.engine = engine;
        System.out.println("Car Bean Created");
    }

    public void drive() {
        System.out.println("Car is Ready");
        engine.start();
    }
}
