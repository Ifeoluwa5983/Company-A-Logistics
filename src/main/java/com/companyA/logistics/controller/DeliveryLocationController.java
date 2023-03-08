package com.companyA.logistics.controller;

import com.companyA.logistics.dto.CreateDeliveryLocation;
import com.companyA.logistics.dto.UpdateDeliveryLocation;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.models.DeliveryLocation;
import com.companyA.logistics.response.ResponseDetails;
import com.companyA.logistics.service.DeliveryLocationServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/locations")
public class DeliveryLocationController {

    @Autowired
    DeliveryLocationServiceImplementation deliveryLocationServiceImplementation;

    @GetMapping("/")
    public ResponseEntity<?> findAllDeliveryLocations() {
        List<DeliveryLocation> deliveryLocations = deliveryLocationServiceImplementation.getDeliveryLocations();
        return new ResponseEntity<>(deliveryLocations, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateDeliveryLocation(@Valid @RequestBody UpdateDeliveryLocation updateDeliveryLocation) throws CompanyAException {
        if (updateDeliveryLocation.getId() == null) {
            throw new CompanyAException("Delivery location id cannot be null");
        }
        deliveryLocationServiceImplementation.updateDeliveryLocation(updateDeliveryLocation);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Deliver location updated successfully", "success");
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeliveryLocation(@PathVariable Integer id) throws CompanyAException {
        deliveryLocationServiceImplementation.deleteDeliveryLocation(id);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Delivery location deleted successfully", "success");
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createDeliveryLocation(@Valid @RequestBody CreateDeliveryLocation createDeliveryLocation) throws CompanyAException {
        deliveryLocationServiceImplementation.createDeliveryLocation(createDeliveryLocation);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Delivery Location has been created successfully", HttpStatus.CREATED.toString());
        return ResponseEntity.status(201).body(responseDetails);
    }
}
