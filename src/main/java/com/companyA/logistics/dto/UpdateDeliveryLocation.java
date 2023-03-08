package com.companyA.logistics.dto;

import lombok.Data;

@Data
public class UpdateDeliveryLocation {

    private Integer id;

    private String location;

    private String state;

    private String country;
}
