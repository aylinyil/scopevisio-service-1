package com.insurance.applicant_service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for WebClient beans used in external service communications.
 * 
 * This configuration sets up the WebClient for making HTTP requests to external services,
 * specifically configured for connecting to the premium calculation service. The base URL
 * is constructed from externalized configuration properties for host and port.
 * 
 * @author Aylin Yilmaz
 */
@Configuration("webClientServiceConfig")
public class WebClientConfig {

    /** Host address for the premium calculation service */
    @Value("${service2.host}")
    private String hostService2;

    /** Port number for the premium calculation service */
    @Value("${service2.port}")
    private String portService2;

    /**
     * Creates and configures a WebClient bean for external service communication.
     * 
     * The WebClient is configured with a base URL constructed from the host and port
     * properties defined in the application configuration. This enables reactive
     * HTTP communication with the premium calculation service.
     * 
     * @param builder the WebClient.Builder instance provided by Spring
     * @return configured WebClient instance ready for use
     */
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        String baseUrl = hostService2 + ":" + portService2;
        return builder.baseUrl(baseUrl).build();
    }
}

