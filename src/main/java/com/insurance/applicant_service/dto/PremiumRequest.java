package com.insurance.applicant_service.dto;


public class PremiumRequest {
    private int yearlyMileage;
    private String vehicleType;
    private String postcode;

    public PremiumRequest(int yearlyMileage, String vehicleType, String postcode) {
        this.yearlyMileage= yearlyMileage;
        this.vehicleType = vehicleType;
        this.postcode = postcode;
    }

    public int getYearlyMileage() {
        return yearlyMileage;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getPostcode() {
        return postcode;
    }
}

