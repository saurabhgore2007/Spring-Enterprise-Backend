package com.saurabh.spring.day03.dependencyinjection.challenge;

import org.springframework.stereotype.Component;

@Component
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void registerUser() {
        System.out.println("Registering User...");
        userRepository.save();
    }
}
