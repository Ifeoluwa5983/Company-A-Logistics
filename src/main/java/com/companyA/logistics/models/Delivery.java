package com.companyA.logistics.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Transient
    private List<String> items ;

    @OneToOne
    private DeliveryLocation pickup;

    @OneToOne
    private DeliveryLocation destination;

    private Double deliveryCost;

}
