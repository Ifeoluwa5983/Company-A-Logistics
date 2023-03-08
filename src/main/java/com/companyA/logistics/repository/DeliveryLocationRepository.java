package com.companyA.logistics.repository;

import com.companyA.logistics.models.DeliveryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryLocationRepository extends JpaRepository<DeliveryLocation, Integer> {
}
