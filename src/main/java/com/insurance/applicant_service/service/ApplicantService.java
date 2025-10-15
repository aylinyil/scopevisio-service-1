package com.insurance.applicant_service.service;

import com.insurance.applicant_service.dto.PremiumRequest;
import com.insurance.applicant_service.dto.PremiumResponse;
import com.insurance.applicant_service.entity.Applicant;
import com.insurance.applicant_service.repository.ApplicantRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Service class for managing insurance applicant business logic.
 * 
 * This service handles the core business operations for insurance applicants including:
 * - Creating new applicants
 * - Calculating insurance premiums via external service integration
 * - Persisting applicant data to the database
 * - Generating HTML-formatted premium information for frontend display
 * 
 * The service integrates with an external premium calculation service using WebClient
 * for reactive HTTP communication and provides robust error handling for service failures.
 * 
 * @author Aylin Yilmaz
 */
@Service
public class ApplicantService {

        /** Logger instance for this service */
        private static final Logger log = LoggerFactory.getLogger(ApplicantService.class);
        
        /** Repository for applicant data persistence */
        private final ApplicantRepository applicantRepository;
        
        /** WebClient for external service communication */
        private final WebClient webClient;

        /**
         * Constructor
         * 
         * @param applicantRepository repository for applicant data operations
         * @param webClient configured WebClient for external service calls
         */
        public ApplicantService(
                ApplicantRepository applicantRepository, WebClient webClient) {
                this.applicantRepository = applicantRepository;
                this.webClient = webClient;
        }

        /**
         * Creates a new insurance applicant with calculated premium information.
         * 
         * This method performs the following operations:
         * 1. Logs the incoming applicant data for audit purposes
         * 2. Prepares a premium calculation request from applicant data
         * 3. Calls the external premium calculation service
         * 4. Handles service failures gracefully with appropriate logging
         * 5. Generates HTML-formatted premium display for frontend use
         * 6. Persists the complete applicant data to the database
         * 
         * All service interactions are logged for monitoring and debugging.
         * 
         * @param applicant the applicant entity containing yearly mileage, vehicle type, and postcode
         * @return the persisted applicant entity with calculated premium and HTML formatting
         * @throws RuntimeException if the external premium service call fails
         */
        public Applicant createApplicant(Applicant applicant) {
                
                log.info("Creating applicant (yearlyMileagee={}, vehicleType={}, postcode={}, )",
                        applicant.getYearlyMileage(), applicant.getVehicleType(), applicant.getPostcode());

                // Prepare PremiumRequest object
                PremiumRequest request = new PremiumRequest(
                                applicant.getYearlyMileage(),
                                applicant.getVehicleType(),
                                applicant.getPostcode());

                log.debug("Sending request to external premium-Service: {}", request);

                PremiumResponse response;
                try {
                response = webClient.post()
                        .uri("/api/premium/calculate")
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(PremiumResponse.class)
                        .block();
                } catch (Exception e) {
                        log.error("Failed to call Premium-Service", e);
                        throw e;
                }

                if (response == null) {
                        log.warn("Premium-Service returned null response");
                        applicant.setCalculatedPremium(null);
                } else {
                        applicant.setCalculatedPremium(response.getCalculatedPremium());
                        log.info("Received premium: {}", response.getCalculatedPremium());
                }

                // Create HTML string for frontend
                String html = String.format(
                                "<label class=\"block text-sm font-medium text-gray-700 mb-1\">Your Premium</label>" +
                                                "<input type=\"text\" readonly value=\"%.2f €\" " +
                                                "class=\"w-full px-4 py-2 border-2 border-gray-300 bg-gray-100 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-400 transition-all\"/>",
                                applicant.getCalculatedPremium());
                        
                applicant.setPremiumDetailsHtml(html);


                // Save applicant in DB
                Applicant saved = applicantRepository.save(applicant);
                log.info("Applicant persisted successfully with id={}", saved.getId());

                return saved;
        }
}