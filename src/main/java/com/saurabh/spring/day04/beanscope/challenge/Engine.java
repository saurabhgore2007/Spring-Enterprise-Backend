package com.saurabh.spring.day04.beanscope.challenge;

import org.springframework.stereotype.Component;

@Component
public class Engine {

    public Engine() {
        System.out.println("Engine Created : " + this);
    }
}