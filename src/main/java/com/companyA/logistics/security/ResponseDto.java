package com.companyA.logistics.security;

import com.companyA.logistics.models.CompanyStaff;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

    private CompanyStaff user;

    private String token;

}
