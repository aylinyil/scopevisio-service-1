package com.insurance.applicant_service.dto;

/**
 * Data Transfer Object (DTO) for premium calculation requests.
 * 
 * This class encapsulates the data required to request a premium calculation
 * from the external premium calculation service. It contains all the necessary
 * applicant information needed to determine insurance premium costs.
 * 
 * 
 * @author Aylin Yilmaz
 */
public class PremiumRequest {
    
    /** Annual mileage driven by the applicant */
    private int yearlyMileage;
    
    /** Type of vehicle owned by the applicant */
    private String vehicleType;
    
    /** Postal code of the applicant's residence */
    private String postcode;

    /**
     * Constructs a new PremiumRequest with the specified parameters.
     * 
     * @param yearlyMileage the annual mileage driven by the applicant
     * @param vehicleType the type of vehicle owned by the applicant
     * @param postcode the postal code of the applicant's residence
     */
    public PremiumRequest(int yearlyMileage, String vehicleType, String postcode) {
        this.yearlyMileage= yearlyMileage;
        this.vehicleType = vehicleType;
        this.postcode = postcode;
    }

    /**
     * Gets the yearly mileage driven by the applicant.
     * 
     * @return the annual mileage in kilometers
     */
    public int getYearlyMileage() {
        return yearlyMileage;
    }

    /**
     * Gets the type of vehicle owned by the applicant.
     * 
     * @return the vehicle type (e.g., "Kleinwagen", "Motorrad", "SUV")
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Gets the postal code of the applicant's residence.
     * 
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }
}

