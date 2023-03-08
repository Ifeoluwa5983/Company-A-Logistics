package com.companyA.logistics.dto;

import com.companyA.logistics.models.Role;
import lombok.Data;

@Data
public class CreateCompanyStaff {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;

    private String password;

    private String image;

    private String role;
}
