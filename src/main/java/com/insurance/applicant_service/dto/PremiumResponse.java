package com.insurance.applicant_service.dto;

public class PremiumResponse {

    private Double calculatedPremium;

    public PremiumResponse() {}

    public PremiumResponse(Double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    public Double getCalculatedPremium() {
        return calculatedPremium;
    }

    public void setCalculatedPremium(Double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    @Override
    public String toString() {
        return "PremiumResponse{" +
                "calculatedPremium=" + calculatedPremium +
                '}';
    }
}

