package com.raimon.dogfriendly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.TipousuarioEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.repository.TipousuarioRepository;

@Service
public class TipousuarioService {

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    public void validate(Long id) {
        if (!oTipousuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public TipousuarioEntity get(Long id) {
        return oTipousuarioRepository.getReferenceById(id);
    }

    public Long update(TipousuarioEntity oTipousuarioEntity) {
        //oAuthService.OnlyAdmins();
        validate(oTipousuarioEntity.getId());
        oTipousuarioRepository.save(oTipousuarioEntity);
        return oTipousuarioEntity.getId();
    }

    public Long count() {
        //oAuthService.OnlyAdmins();
        return oTipousuarioRepository.count();
    }

    public Page<TipousuarioEntity> getPage(int page, int size) {
        //oAuthService.OnlyAdmins();
        Pageable oPageable = PageRequest.of(page, size);
            return oTipousuarioRepository.findAll(oPageable);
    }

}
