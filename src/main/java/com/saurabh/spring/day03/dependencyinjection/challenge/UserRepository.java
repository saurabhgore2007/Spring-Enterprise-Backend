package com.saurabh.spring.day03.dependencyinjection.challenge;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {

	public void save() {
        System.out.println("User saved to database");
    }
}
