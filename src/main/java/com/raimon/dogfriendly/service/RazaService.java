package com.raimon.dogfriendly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.RazaEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.helper.ValidationHelper;
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

    public RazaEntity get(Long id) {
        return oRazaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(RazaEntity oNewRazaEntity) {
        // oAuthService.OnlyAdmins();
        // validate(oNewUsuarioEntity);
        return oRazaRepository.save(oNewRazaEntity).getId();
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oRazaRepository.count();
    }

    public Long update(RazaEntity oRazaEntity) {
        validate(oRazaEntity.getId());
        // oAuthService.OnlyAdmins();
        RazaEntity oOldRazaEntity = oRazaRepository.getReferenceById(oRazaEntity.getId());
        return oRazaRepository.save(oRazaEntity).getId();
    }

    public Long delete(Long id) {
    /*     oAuthService.OnlyAdmins(); */
        validate(id);
        oRazaRepository.deleteById(id);
        if (oRazaRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public Page<RazaEntity> getPage(Pageable oPageable, String strFilter) {
        // oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<RazaEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oRazaRepository.findAll(oPageable);
        } else {
            oPage = oRazaRepository
                    .findByNombre(strFilter, oPageable);
        }
        return oPage;
    }

}
