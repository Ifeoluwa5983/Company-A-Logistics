package com.companyA.logistics.controller;

import com.companyA.logistics.dto.CreateDelivery;
import com.companyA.logistics.dto.CreateDeliveryLocation;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.response.DeliveryResponse;
import com.companyA.logistics.response.ResponseDetails;
import com.companyA.logistics.response.ResponseDetailsWithObject;
import com.companyA.logistics.service.DeliveryServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @Autowired
    DeliveryServiceImplementation deliveryServiceImplementation;

    @PostMapping("/")
    public ResponseEntity<?> createDeliveryLocation(@Valid @RequestBody CreateDelivery createDelivery) throws CompanyAException {
        DeliveryResponse deliveryResponse = deliveryServiceImplementation.createDelivery(createDelivery);
        ResponseDetailsWithObject responseDetails = new ResponseDetailsWithObject(LocalDateTime.now(), "Delivery has been created successfully", deliveryResponse, HttpStatus.CREATED.toString());
        return ResponseEntity.status(201).body(responseDetails);
    }
}
