package com.sergio.learningspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* This annotation:
* - Designates this class as the entrypoint of all the app
* - Initiates Spring's auto configuration based on the jars that are in our class path
* - This is a configuration class and we can put Bean configuration here if we choose */
@SpringBootApplication
public class LearningSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}
}
