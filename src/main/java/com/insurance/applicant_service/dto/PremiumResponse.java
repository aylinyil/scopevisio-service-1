package com.insurance.applicant_service.dto;

/**
 * Data Transfer Object (DTO) for premium calculation responses.
 * 
 * This class encapsulates the response from the external premium calculation service.
 * It contains the calculated premium amount that can be used for insurance quotes
 * and applicant data storage.
 * 
 * @author Aylin Yilmaz
 */
public class PremiumResponse {

    /** The calculated insurance premium amount */
    private Double calculatedPremium;

    /**
     * Constructs a new PremiumResponse with the specified calculated premium.
     * 
     * @param calculatedPremium the calculated premium amount
     */
    public PremiumResponse(Double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    /**
     * Gets the calculated insurance premium amount.
     * 
     * @return the premium amount in the configured currency, or null if calculation failed
     */
    public Double getCalculatedPremium() {
        return calculatedPremium;
    }

    /**
     * Sets the calculated insurance premium amount.
     * 
     * @param calculatedPremium the premium amount to set
     */
    public void setCalculatedPremium(Double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    /**
     * Returns a string representation of the PremiumResponse.
     * 
     * @return formatted string containing the calculated premium value
     */
    @Override
    public String toString() {
        return "PremiumResponse{" +
                "calculatedPremium=" + calculatedPremium +
                '}';
    }
}

