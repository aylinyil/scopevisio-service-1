package com.insurance.applicant_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicantServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ApplicantServiceApplication.class);

    public static void main(String[] args) {
        log.info("Starting Applicant Service Application...");
        SpringApplication.run(ApplicantServiceApplication.class, args);
        log.info("Applicant Service Application started successfully.");
    }

}
