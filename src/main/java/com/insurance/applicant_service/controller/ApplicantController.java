package com.insurance.applicant_service.controller;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.applicant_service.entity.Applicant;
import com.insurance.applicant_service.service.ApplicantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * REST controller for managing insurance applicant operations.
 * 
 * This controller provides endpoints for creating and managing insurance applicants.
 * It handles HTTP requests related to applicant data, coordinates with the service layer
 * for business logic, and returns appropriate responses including calculated premium information.
 * 
 * All endpoints are accessible via cross-origin requests to support frontend integration.
 * 
 * @author Aylin Yilmaz
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ApplicantController {
    
    /** Logger instance for this controller */
    private static final Logger log = LoggerFactory.getLogger(ApplicantController.class);
    
    /** Service layer dependency for applicant business logic */
    private final ApplicantService applicantService;

    /**
     * Constructor
     * 
     * @param applicantService the service instance for handling applicant business logic
     */
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    /**
     * Creates a new insurance applicant and calculates their premium.
     * 
     * This endpoint accepts applicant data in JSON format, processes it through the service layer
     * to calculate the insurance premium, persists the applicant data, and returns HTML-formatted
     * premium information suitable for frontend display.
     * 
     * The premium calculation involves calling an external service.
     * 
     * @param applicant the applicant data containing yearly mileage, vehicle type, and postcode
     * @return ResponseEntity containing HTML-formatted premium details with HTTP 200 status
     * @throws RuntimeException if there's an error communicating with the premium calculation service
     */
    @PostMapping(
    value = "/applicants",
    consumes = "application/json",
    produces = "text/html"
    )
    public ResponseEntity<String> createApplicant(@RequestBody Applicant applicant) {

        log.info("Received request to create applicant");
        Applicant saved = applicantService.createApplicant(applicant);
        log.info("Applicant created successfully with id={}", saved.getId());
        
        return ResponseEntity.ok(saved.getPremiumDetailsHtml());
    }

}
