package com.microservice.pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.microservice.pattern")
public class PatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternApplication.class, args);
	}

}
