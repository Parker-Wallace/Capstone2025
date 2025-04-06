package com.insurance.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.insurance.capstone")
public class CapstoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapstoneApplication.class, args);
    }
}