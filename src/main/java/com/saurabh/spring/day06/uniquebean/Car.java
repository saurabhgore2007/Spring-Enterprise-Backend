package com.saurabh.spring.day06.uniquebean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private Engine engine;

    public Car(@Qualifier("dieselEngine")Engine engine) {
        this.engine = engine;
        System.out.println("Car Created");
    }
}
