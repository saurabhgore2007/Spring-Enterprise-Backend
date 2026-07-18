package com.saurabh.spring.day02.applicationcontext;

public class Car {

	private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
        System.out.println("Car Bean Created");
    }

    public void drive() {
        System.out.println("Car is Ready to Drive");
        engine.start();
    }

}
