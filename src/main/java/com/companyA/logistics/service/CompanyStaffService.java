package com.companyA.logistics.service;

import com.companyA.logistics.dto.CreateCompanyStaff;
import com.companyA.logistics.exception.CompanyAException;

public interface CompanyStaffService {

    void register(CreateCompanyStaff createCompanyStaff) throws CompanyAException;
}
