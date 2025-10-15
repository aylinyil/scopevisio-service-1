package com.insurance.applicant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.applicant_service.entity.Applicant;

/**
 * Repository interface for managing Applicant entity persistence operations.
 * 
 * This repository extends Spring Data JPA's JpaRepository to provide standard CRUD operations
 * for the Applicant entity. It automatically handles database interactions including
 * create, read, update, and delete operations without requiring explicit implementation.
 * 
 * The repository is automatically implemented by Spring Data JPA at runtime,
 * providing all basic database operations plus additional query methods as needed.
 * 
 * @author Aylin Yilmaz
 * @see Applicant
 * @see JpaRepository
 */
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    
}
