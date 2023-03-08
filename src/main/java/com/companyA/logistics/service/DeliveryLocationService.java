package com.companyA.logistics.service;

import com.companyA.logistics.dto.CreateDeliveryLocation;
import com.companyA.logistics.dto.UpdateDeliveryLocation;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.models.DeliveryLocation;

import java.util.List;

public interface DeliveryLocationService {

    void createDeliveryLocation(CreateDeliveryLocation createDeliveryLocation) throws CompanyAException;

    List<DeliveryLocation> getDeliveryLocations();

    void deleteDeliveryLocation(Integer id) throws CompanyAException;

    void updateDeliveryLocation(UpdateDeliveryLocation updateDeliveryLocation) throws CompanyAException;
}
