package com.saurabh.spring.day06.uniquebean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PetrolEngine implements Engine{

	public PetrolEngine() {
        System.out.println("Petrol Engine Created");
    }

    @Override
    public void start() {
        System.out.println("Petrol Engine Started");
    }
}
