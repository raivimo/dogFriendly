package com.raimon.dogfriendly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.PaseoEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.repository.PaseoRepository;

@Service
public class PaseoService {
    @Autowired
    PaseoRepository oPaseoRepository;

    @Autowired
    AuthService oAuthService;


    public void validate(Long id) {
        if (!oPaseoRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oPaseoRepository.count();
    }


    public PaseoEntity get(Long id) {
        return oPaseoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(PaseoEntity oNewPaseoEntity) {
        oAuthService.OnlyAdmins();
        //validate(oNewUsuarioEntity);
        oNewPaseoEntity.setId(0L);
        return oPaseoRepository.save(oNewPaseoEntity).getId();
    }


    public Long update(PaseoEntity oPaseoEntity) {
        validate(oPaseoEntity.getId());
        // oAuthService.OnlyAdmins();
        PaseoEntity oOldPaseoEntity = oPaseoRepository.getReferenceById(oPaseoEntity.getId());
        return oPaseoRepository.save(oPaseoEntity).getId();
    }

    public Long delete(Long id) {
        oAuthService.OnlyAdmins();
        validate(id);
        oPaseoRepository.deleteById(id);
        if (oPaseoRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }









    
}
