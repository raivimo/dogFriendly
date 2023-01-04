package com.raimon.dogfriendly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.PerroEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.repository.PerroRepository;

@Service
public class PerroService {

    @Autowired
    PerroRepository oPerroRepository;

    @Autowired
    AuthService oAuthService;
    
    public void validate(Long id) {
        if (!oPerroRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oPerroRepository.count();
    }


    public PerroEntity get(Long id) {
        return oPerroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(PerroEntity oNewPerroEntity) {
        oAuthService.OnlyAdmins();
        //validate(oNewUsuarioEntity);
        oNewPerroEntity.setId(0L);
        return oPerroRepository.save(oNewPerroEntity).getId();
    }


    public Long update(PerroEntity oPerroEntity) {
        validate(oPerroEntity.getId());
        // oAuthService.OnlyAdmins();
        PerroEntity oOldPerroEntity = oPerroRepository.getReferenceById(oPerroEntity.getId());
        return oPerroRepository.save(oPerroEntity).getId();
    }

    public Long delete(Long id) {
        oAuthService.OnlyAdmins();
        validate(id);
        oPerroRepository.deleteById(id);
        if (oPerroRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }


}
