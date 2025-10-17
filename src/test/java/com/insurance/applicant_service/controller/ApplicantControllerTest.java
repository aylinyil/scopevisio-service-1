package com.insurance.applicant_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.applicant_service.entity.Applicant;
import com.insurance.applicant_service.service.ApplicantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for ApplicantController.
 * 
 * These tests verify the HTTP layer behavior of the ApplicantController
 * The service layer is mocked to isolate controller logic.
 * 
 * @author Aylin Yilmaz
 */
@ExtendWith(MockitoExtension.class)
class ApplicantControllerTest {

    @Mock
    private ApplicantService applicantService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ApplicantController(applicantService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void create_ok() throws Exception {
        Applicant inputApplicant = new Applicant();
        inputApplicant.setYearlyMileage(15000);
        inputApplicant.setVehicleType("Kleinwagen");
        inputApplicant.setPostcode("12345");

        Applicant savedApplicant = new Applicant();
        savedApplicant.setId(1L);
        savedApplicant.setYearlyMileage(15000);
        savedApplicant.setVehicleType("Kleinwagen");
        savedApplicant.setPostcode("12345");
        savedApplicant.setCalculatedPremium(250.75);
        savedApplicant.setPremiumDetailsHtml("<label class=\"block text-sm font-medium text-gray-700 mb-1\">Your Premium</label>" +
                "<input type=\"text\" readonly value=\"250.75 ?\" " +
                "class=\"w-full px-4 py-2 border-2 border-gray-300 bg-gray-100 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-400 transition-all\"/>");

        when(applicantService.createApplicant(any(Applicant.class))).thenReturn(savedApplicant);

        mockMvc.perform(post("/api/applicants")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputApplicant)))
               .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(savedApplicant.getPremiumDetailsHtml()));
    }
}
