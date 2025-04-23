package com.sander.fresco.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class SanderFrescoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanderFrescoServiceApplication.class, args);
	}

	@GetMapping("/home")
public String getAllStudents() {
 return "welcome on home page";
}

	@GetMapping("/")
public String getAllStudentsss() {
 return "Application is UP";
}

}
