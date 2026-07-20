package com.saurabh.spring.day04.beanscope.challenge;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Car {

    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
        System.out.println("Car Created : " + this);
    }

    public void show() {
        System.out.println("Car : " + this);
        System.out.println("Engine : " + engine);
    }
}
