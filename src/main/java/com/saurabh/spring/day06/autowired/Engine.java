package com.saurabh.spring.day06.autowired;

import org.springframework.stereotype.Component;

@Component
public class Engine {

	public Engine() {
        System.out.println("Engine Bean Created");
	}
}
