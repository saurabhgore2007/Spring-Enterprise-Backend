package com.saurabh.spring.day06.uniquebean;

import org.springframework.stereotype.Component;

@Component
public class DieselEngine implements Engine {

    public DieselEngine() {
        System.out.println("Diesel Engine Created");
    }

    @Override
    public void start() {
        System.out.println("Diesel Engine Started");
    }
}
