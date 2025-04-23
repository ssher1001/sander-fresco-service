package com.sander.fresco.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SanderFrescoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanderFrescoServiceApplication.class, args);
	}

	@GetMapping("/home")
public String getAllStudents() {
 return "welcome on home page";
}

	@GetMapping("/")
public String getAllStudents() {
 return "Application is UP";
}

}
