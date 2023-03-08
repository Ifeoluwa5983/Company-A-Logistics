package com.companyA.logistics.service;

import com.companyA.logistics.dto.CreateDeliveryLocation;
import com.companyA.logistics.dto.UpdateDeliveryLocation;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.models.DeliveryLocation;
import com.companyA.logistics.repository.DeliveryLocationRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryLocationServiceImplementation implements DeliveryLocationService{

    @Autowired
    DeliveryLocationRepository deliveryLocationRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void createDeliveryLocation(CreateDeliveryLocation createDeliveryLocation) throws CompanyAException {
        try {
            DeliveryLocation deliveryLocation = new DeliveryLocation();
            modelMapper.map(createDeliveryLocation, deliveryLocation);
            deliveryLocation.setCreatedDate(LocalDate.now().toString());
            deliveryLocationRepository.save(deliveryLocation);
        }
        catch (Exception exception){
            throw new CompanyAException("An error occurred in delivery location creation");
        }
    }

    @Override
    public List<DeliveryLocation> getDeliveryLocations() {
        return deliveryLocationRepository.findAll();
    }

    @Override
    public void deleteDeliveryLocation(Integer id) throws CompanyAException {
        try {
            DeliveryLocation deliveryLocation = deliveryLocationRepository.findById(id).orElse(null);
            assert deliveryLocation != null;
            if (deliveryLocation == null){
                throw new CompanyAException("This delivery Location has been deleted already");
            }
            deliveryLocationRepository.deleteById(id);
        } catch (Exception exception){
            throw new CompanyAException("An error occurred");
        }
    }

    @Override
    public void updateDeliveryLocation(UpdateDeliveryLocation updateDeliveryLocation) throws CompanyAException {
        try {
            DeliveryLocation deliveryLocation = deliveryLocationRepository.findById(updateDeliveryLocation.getId()).get();
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(updateDeliveryLocation, deliveryLocation);
            deliveryLocation.setModifiedDate(LocalDate.now().toString());
            deliveryLocationRepository.save(deliveryLocation);
        }
        catch (Exception exception){
            throw new CompanyAException("An error occurred during delivery location updating");
        }
    }
}
