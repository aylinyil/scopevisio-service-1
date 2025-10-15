package com.insurance.applicant_service.entity;

import jakarta.persistence.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "application")
public class Applicant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postcode;

    @Column(name = "yearly_mileage")
    private int yearlyMileage;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "calculated_premium")
    private Double calculatedPremium;

    @Transient
    private String premiumDetailsHtml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYearlyMileage() {
        return yearlyMileage;
    }

    public void setYearlyMileage(int yearlyMileage) {
        this.yearlyMileage = yearlyMileage;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Double getCalculatedPremium() {
        return calculatedPremium;
    }

    public void setCalculatedPremium(Double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    public String getPremiumDetailsHtml() {
        return premiumDetailsHtml;
    }
    public void setPremiumDetailsHtml(String premiumDetailsHtml) {
        this.premiumDetailsHtml = premiumDetailsHtml;
    }

}

