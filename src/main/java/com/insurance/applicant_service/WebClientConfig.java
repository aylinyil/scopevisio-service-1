package com.insurance.applicant_service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration("webClientServiceConfig")
public class WebClientConfig {

    @Value("${service2.host}")
    private String hostService2;

    @Value("${service2.port}")
    private String portService2;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        String baseUrl = hostService2 + ":" + portService2;
        return builder.baseUrl(baseUrl).build();
    }
}

