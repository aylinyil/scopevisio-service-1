package com.insurance.applicant_service.service;

import com.insurance.applicant_service.dto.PremiumRequest;
import com.insurance.applicant_service.dto.PremiumResponse;
import com.insurance.applicant_service.entity.Applicant;
import com.insurance.applicant_service.repository.ApplicantRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApplicantService {

        private static final Logger log = LoggerFactory.getLogger(ApplicantService.class);
        private final ApplicantRepository applicantRepository;
        private final WebClient webClient;

        public ApplicantService(
                ApplicantRepository applicantRepository, WebClient webClient) {
                this.applicantRepository = applicantRepository;
                this.webClient = webClient;
        }

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