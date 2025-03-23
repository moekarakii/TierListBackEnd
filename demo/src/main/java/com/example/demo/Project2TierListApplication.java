package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @SpringBootApplication(scanBasePackages = "com.example.demo")

@SpringBootApplication
@RestController
public class Project2TierListApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2TierListApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hi there  %s!", name);
	}

	@RequestMapping("/")
	public String home() {
		return "This is my app";
	}

}
