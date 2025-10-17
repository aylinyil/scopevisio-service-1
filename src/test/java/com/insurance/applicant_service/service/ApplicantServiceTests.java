package com.insurance.applicant_service.service;

import com.insurance.applicant_service.dto.PremiumRequest;
import com.insurance.applicant_service.dto.PremiumResponse;
import com.insurance.applicant_service.entity.Applicant;
import com.insurance.applicant_service.repository.ApplicantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ApplicantService.
 * 
 * These tests verify the business logic of the ApplicantService class using Mockito
 * to mock external dependencies (WebClient and ApplicantRepository). 
 * 
 * @author Aylin Yilmaz
 */
@ExtendWith(MockitoExtension.class)
class ApplicantServiceTests {

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private ApplicantService applicantService;

    @BeforeEach
    void setUp() {
        applicantService = new ApplicantService(applicantRepository, webClient);
    }

    @Test
    void calculate_ok() {
        Applicant inputApplicant = new Applicant();
        inputApplicant.setYearlyMileage(15000);
        inputApplicant.setVehicleType("Kleinwagen");
        inputApplicant.setPostcode("12345");

        PremiumResponse premiumResponse = new PremiumResponse(250.75);

        doReturn(requestBodyUriSpec).when(webClient).post();
        doReturn(requestBodySpec).when(requestBodyUriSpec).uri(anyString());
        doReturn(requestBodySpec).when(requestBodySpec).bodyValue(any(PremiumRequest.class));
        doReturn(responseSpec).when(requestBodySpec).retrieve();
        doReturn(Mono.just(premiumResponse)).when(responseSpec).bodyToMono(PremiumResponse.class);

        when(applicantRepository.save(any(Applicant.class))).thenAnswer(invocation -> {
            Applicant a = invocation.getArgument(0);
            a.setId(1L);
            return a;
        });

        Applicant result = applicantService.createApplicant(inputApplicant);

        assertNotNull(result.getId());
        assertEquals(250.75, result.getCalculatedPremium());
        assertNotNull(result.getPremiumDetailsHtml());
        assertTrue(result.getPremiumDetailsHtml().contains("Your Premium"));
    }

    @Test
    void calculate_error() {
        Applicant inputApplicant = new Applicant();
        inputApplicant.setYearlyMileage(15000);
        inputApplicant.setVehicleType("Kleinwagen");
        inputApplicant.setPostcode("12345");

        doReturn(requestBodyUriSpec).when(webClient).post();
        doReturn(requestBodySpec).when(requestBodyUriSpec).uri(anyString());
        doReturn(requestBodySpec).when(requestBodySpec).bodyValue(any(PremiumRequest.class));
        doReturn(responseSpec).when(requestBodySpec).retrieve();
        doReturn(Mono.error(new RuntimeException("Service unavailable"))).when(responseSpec).bodyToMono(PremiumResponse.class);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> applicantService.createApplicant(inputApplicant));
        assertEquals("Service unavailable", ex.getMessage());
        verify(applicantRepository, never()).save(any(Applicant.class));
    }
}