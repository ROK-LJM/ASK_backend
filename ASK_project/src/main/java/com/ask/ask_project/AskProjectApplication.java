package com.ask.ask_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AskProjectApplication {

//	public static final String APPLICATION_LOCATIONS = "spring.config.location="
//			+ "classpath:application.yml,"
//			+ "classpath:application-credentials.yml";
//
//	public static void main(String[] args) {
//		new SpringApplicationBuilder(AskProjectApplication.class)
//				.properties(APPLICATION_LOCATIONS)
//				.run(args);
//	}

	public static void main(String[] args) {
		SpringApplication.run(AskProjectApplication.class, args);
	}
}
