package com.companyA.logistics.repository;

import com.companyA.logistics.models.CompanyStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyStaffRepository extends JpaRepository<CompanyStaff, Integer> {

    CompanyStaff findByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String email);
}
