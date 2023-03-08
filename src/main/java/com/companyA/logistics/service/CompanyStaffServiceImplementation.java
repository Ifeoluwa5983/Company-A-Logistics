package com.companyA.logistics.service;

import com.companyA.logistics.dto.CreateCompanyStaff;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.models.CompanyStaff;
import com.companyA.logistics.repository.CompanyStaffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompanyStaffServiceImplementation implements CompanyStaffService {

    @Autowired
    CompanyStaffRepository companyStaffRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void register(CreateCompanyStaff createCompanyStaff) throws CompanyAException {
        try {
            if(companyStaffRepository.existsByEmailAddress(createCompanyStaff.getEmailAddress())){
                throw new CompanyAException("OOps a user with that email exist");
            }
            CompanyStaff companyStaff = new CompanyStaff();
            modelMapper.map(createCompanyStaff, companyStaff);
            companyStaff.setCreatedDate(LocalDate.now().toString());
            companyStaff.setActive(true);
            companyStaff.setPassword(encryptPassword(createCompanyStaff.getPassword()));
            companyStaffRepository.save(companyStaff);
        }
        catch (Exception exception){
            throw new CompanyAException("An error occurred in account creation");
        }
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
}
