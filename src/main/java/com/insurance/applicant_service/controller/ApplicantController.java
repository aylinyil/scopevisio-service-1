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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ApplicantController {
    
    private static final Logger log = LoggerFactory.getLogger(ApplicantController.class);
    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

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
