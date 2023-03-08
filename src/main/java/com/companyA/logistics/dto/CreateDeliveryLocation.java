package com.companyA.logistics.dto;

import lombok.Data;

@Data
public class CreateDeliveryLocation {

    private String location;

    private String state;

    private String country;
}
