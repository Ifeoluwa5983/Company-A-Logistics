package com.companyA.logistics.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "Email is mandatory")
    private String emailAddress;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
