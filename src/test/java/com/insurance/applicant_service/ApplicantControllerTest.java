package com.insurance.applicant_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public @SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApplicantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateApplicant() throws Exception {
        String json = """
            {
              "yearlyKm": 12000,
              "vehicleType": "SUV",
              "postcode": "10115"
            }
        """;

        mockMvc.perform(post("/api/applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
}
 
