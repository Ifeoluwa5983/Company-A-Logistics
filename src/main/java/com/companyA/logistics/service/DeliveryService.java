package com.companyA.logistics.service;

import com.companyA.logistics.dto.CreateDelivery;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.response.DeliveryResponse;

public interface DeliveryService {

    DeliveryResponse createDelivery(CreateDelivery createDelivery) throws CompanyAException;
}
