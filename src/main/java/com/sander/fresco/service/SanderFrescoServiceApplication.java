package com.sander.fresco.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SanderFrescoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanderFrescoServiceApplication.class, args);
	}

	@GetMapping("/home")
public List<Student> getAllStudents() {
 return "welcome on home page";
}

	@GetMapping("/")
public List<Student> getAllStudents() {
 return "Application is UP";
}

}
