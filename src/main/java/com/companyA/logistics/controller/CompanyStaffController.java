package com.companyA.logistics.controller;

import com.companyA.logistics.dto.CreateCompanyStaff;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.response.ResponseDetails;
import com.companyA.logistics.service.CompanyStaffServiceImpl;
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
@RequestMapping("/api/v1/user")
public class CompanyStaffController {

    @Autowired
    CompanyStaffServiceImpl companyStaffService;

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateCompanyStaff applicationUser) throws CompanyAException {
        log.info("Account creation started successfully");
        companyStaffService.register(applicationUser);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Your account has been created successfully", HttpStatus.CREATED.toString());
        return ResponseEntity.status(201).body(responseDetails);
    }
}
