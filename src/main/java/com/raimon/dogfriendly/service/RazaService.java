package com.raimon.dogfriendly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.RazaEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.repository.RazaRepository;

@Service
public class RazaService {

    @Autowired
    RazaRepository oRazaRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oRazaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oRazaRepository.count();
    }


    public RazaEntity get(Long id) {
        return oRazaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(RazaEntity oNewRazaEntity) {
        oAuthService.OnlyAdmins();
        //validate(oNewUsuarioEntity);
        oNewRazaEntity.setId(0L);
        return oRazaRepository.save(oNewRazaEntity).getId();
    }


    public Long update(RazaEntity oRazaEntity) {
        validate(oRazaEntity.getId());
        // oAuthService.OnlyAdmins();
        RazaEntity oOldRazaEntity = oRazaRepository.getReferenceById(oRazaEntity.getId());
        return oRazaRepository.save(oRazaEntity).getId();
    }

    public Long delete(Long id) {
        oAuthService.OnlyAdmins();
        validate(id);
        oRazaRepository.deleteById(id);
        if (oRazaRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    
}
