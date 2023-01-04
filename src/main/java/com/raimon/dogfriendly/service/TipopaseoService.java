package com.raimon.dogfriendly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.TipopaseoEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.repository.TipopaseoRepository;

@Service
public class TipopaseoService {
    @Autowired
    TipopaseoRepository oTipopaseoRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oTipopaseoRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oTipopaseoRepository.count();
    }


    public TipopaseoEntity get(Long id) {
        return oTipopaseoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(TipopaseoEntity oNewTipopaseoEntity) {
        oAuthService.OnlyAdmins();
        //validate(oNewUsuarioEntity);
        oNewTipopaseoEntity.setId(0L);
        return oTipopaseoRepository.save(oNewTipopaseoEntity).getId();
    }


    public Long update(TipopaseoEntity oTipopaseoEntity) {
        validate(oTipopaseoEntity.getId());
        // oAuthService.OnlyAdmins();
        TipopaseoEntity oOldTipopaseoEntity = oTipopaseoRepository.getReferenceById(oTipopaseoEntity.getId());
        return oTipopaseoRepository.save(oTipopaseoEntity).getId();
    }

    public Long delete(Long id) {
        oAuthService.OnlyAdmins();
        validate(id);
        oTipopaseoRepository.deleteById(id);
        if (oTipopaseoRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }



}
