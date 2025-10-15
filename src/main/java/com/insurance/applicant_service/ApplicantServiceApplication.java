package com.insurance.applicant_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Insurance Applicant Service.
 * 
 * This microservice handles insurance applicant data management and premium calculation
 * by integrating with external premium calculation services. It provides RESTful APIs
 * for creating applicants and retrieving calculated premium information.
 * 
 * @author Aylin Yilmaz
 */
@SpringBootApplication
public class ApplicantServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ApplicantServiceApplication.class);

    /**
     * Main entry point for the Insurance Applicant Service application.
     * 
     * Initializes and starts the Spring Boot application with default configuration.
     * Logs application startup events for monitoring and debugging purposes.
     * 
     */
    public static void main(String[] args) {
        log.info("Starting Applicant Service Application...");
        SpringApplication.run(ApplicantServiceApplication.class, args);
        log.info("Applicant Service Application started successfully.");
    }

}
