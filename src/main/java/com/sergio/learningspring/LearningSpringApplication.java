package com.sergio.learningspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* This annotation:
* - Designates this class as the entrypoint of all the app
* - Initiates Spring's auto configuration based on the jars that are in our class path
* - This is a configuration class and we can put Bean configuration here if we choose */
@SpringBootApplication
public class LearningSpringApplication {

	/* Standard way of getting an application wide logger with slf4j */
	private static final Logger LOG = LoggerFactory.getLogger(LearningSpringApplication.class);


	public static void main(String[] args) {
		/* This is how you use the logger */
		LOG.info("Starting the application!");
		SpringApplication.run(LearningSpringApplication.class, args);
	}
}
