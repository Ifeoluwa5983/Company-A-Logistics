package com.companyA.logistics.dto;

import lombok.Data;

@Data
public class CreateDelivery {

    private String item1 ;

    private String item2 ;

    private String item3 ;

    private String item4 ;

    private String item5 ;

    private Integer pickup;

    private Integer destination;
}
