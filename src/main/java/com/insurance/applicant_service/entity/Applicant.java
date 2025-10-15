package com.insurance.applicant_service.entity;

import jakarta.persistence.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing an insurance applicant.
 * 
 * This JPA entity maps to the 'application' table in the database and contains
 * all the necessary information for processing insurance applications including
 * applicant details, calculated premium, and HTML formatting for frontend display.
 * 
 * The entity includes both persistent fields (stored in database) and transient fields
 * (calculated at runtime for presentation purposes).
 * 
 * @author Aylin Yilmaz
 */
@Entity
@Table(name = "application")
public class Applicant {

    /** Unique identifier for the applicant, auto-generated */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Postal code of the applicant's residence */
    private String postcode;

    /** Annual mileage driven by the applicant */
    @Column(name = "yearly_mileage")
    private int yearlyMileage;

    /** Type of vehicle owned by the applicant */
    @Column(name = "vehicle_type")
    private String vehicleType;

    /** Calculated insurance premium amount */
    @Column(name = "calculated_premium")
    private Double calculatedPremium;

    /** HTML-formatted premium details for frontend display (not persisted) */
    @Transient
    private String premiumDetailsHtml;

    /**
     * Gets the unique identifier of the applicant.
     * 
     * @return the applicant's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the applicant.
     * 
     * @param id the applicant's ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the yearly mileage driven by the applicant.
     * 
     * @return the annual mileage in miles/kilometers
     */
    public int getYearlyMileage() {
        return yearlyMileage;
    }

    /**
     * Sets the yearly mileage driven by the applicant.
     * 
     * @param yearlyMileage the annual mileage to set
     */
    public void setYearlyMileage(int yearlyMileage) {
        this.yearlyMileage = yearlyMileage;
    }

    /**
     * Gets the type of vehicle owned by the applicant.
     * 
     * @return the vehicle type (e.g., "Car", "Motorcycle", "Truck")
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the type of vehicle owned by the applicant.
     * 
     * @param vehicleType the vehicle type to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the postal code of the applicant's residence.
     * 
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the postal code of the applicant's residence.
     * 
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets the calculated insurance premium amount.
     * 
     * @return the premium amount in the configured currency, or null if not calculated
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
     * Gets the HTML-formatted premium details for frontend display.
     * 
     * This field is transient and not persisted to the database.
     * It contains ready-to-use HTML markup for displaying premium information in the UI.
     * 
     * @return HTML string containing formatted premium details
     */
    public String getPremiumDetailsHtml() {
        return premiumDetailsHtml;
    }
    
    /**
     * Sets the HTML-formatted premium details for frontend display.
     * 
     * This field is transient and not persisted to the database.
     * 
     * @param premiumDetailsHtml HTML string to set
     */
    public void setPremiumDetailsHtml(String premiumDetailsHtml) {
        this.premiumDetailsHtml = premiumDetailsHtml;
    }

}

