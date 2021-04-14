package com.familyapp.services.impl;

import com.familyapp.models.entities.Family;
import com.familyapp.models.serviceModels.FamilyRegistrationServModel;
import com.familyapp.repositories.FamilyRepo;
import com.familyapp.services.FamilyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepo familyRepo;
    private final ModelMapper modelMapper;

    public FamilyServiceImpl(FamilyRepo familyRepo, ModelMapper modelMapper) {
        this.familyRepo = familyRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public Family findByEmail(String familyEmail) {
        return familyRepo.findByEmail(familyEmail).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean emailExists(String familyEmail) {
        return familyRepo.findByEmail(familyEmail).isPresent();
    }

    @Override
    public Family findById(Long id) {
        return familyRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void registerFamily(FamilyRegistrationServModel familyServiceModel) {
        Family newFamily = modelMapper.map(familyServiceModel, Family.class);

        familyRepo.save(newFamily);
    }
}
