package com.saurabh.spring.day02.applicationcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
    public Engine engine() {
        return new Engine();
    }
	
	@Bean
    public Car car() {
        return new Car(engine());
    }
}
